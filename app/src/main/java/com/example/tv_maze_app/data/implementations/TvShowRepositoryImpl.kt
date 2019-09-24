package com.example.tv_maze_app.data.implementations

import androidx.lifecycle.MutableLiveData
import com.example.tv_maze_app.data.db.AppDatabase
import com.example.tv_maze_app.data.models.*
import com.example.tv_maze_app.data.network.ServiceGenerator
import com.example.tv_maze_app.data.network.services.TvShowService
import com.example.tv_maze_app.domain.repositories.TvShowRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*

class TvShowRepositoryImpl : TvShowRepository {

    private var mMutableLiveData = MutableLiveData<ResultState<List<TvShow>>>()

    private val mService by lazy {
        ServiceGenerator.createService(TvShowService::class.java)
    }

    override fun doLoadTvShowList(scope: CoroutineScope): MutableLiveData<ResultState<List<TvShow>>> {
        scope.launch(Dispatchers.Main) {
            try {
                val list = getTvShowListDb()
                if (!list.isNullOrEmpty()) {
                    mMutableLiveData.value = ResultState.Loading()
                    mMutableLiveData.value = ResultState.Success(list)
                } else {
                    doRefreshTvShowList(scope)
                }
            } catch (e: Exception) {
                Timber.e(e)
                mMutableLiveData.value = ResultState.Failure(throwable = e)
            }
        }
        return mMutableLiveData
    }

    override fun doRefreshTvShowList(scope: CoroutineScope): MutableLiveData<ResultState<List<TvShow>>> {
        mMutableLiveData.value = ResultState.Loading()

        scope.launch(Dispatchers.Main) {
            val request = mService.getTvShowList()

            withContext(Dispatchers.IO) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        response.body()?.let { list ->
                            parseTvShow(list)
                            mMutableLiveData.postValue(ResultState.Success(getTvShowListDb()))
                        }
                    } else {
                        Timber.d(response.message())
                        mMutableLiveData.postValue(ResultState.Error(error = response.message()))
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                    mMutableLiveData.postValue(ResultState.Failure(throwable = e))
                }
            }
        }
        return mMutableLiveData
    }

    private suspend fun parseTvShow(list: List<TvShow>) {
        AppDatabase.getInstance().tvShowDao().deleteAll()

        for (tvShow in list) {
            AppDatabase.getInstance().tvShowDao().insert(tvShow)
            parseRate(tvShow.id, tvShow.rate)
            parseNetwork(tvShow.id, tvShow.network)
            parseImage(tvShow.id, tvShow.image)
        }
    }

    private suspend fun parseRate(id: Long?, rate: Rate?) {
        rate?.let {
            it.tvShowId = id
            AppDatabase.getInstance().rateDao().insert(it)
        }
    }

    private suspend fun parseNetwork(id: Long?, network: Network?) {
        network?.let {
            it.tvShowId = id
            AppDatabase.getInstance().networkDao().insert(network)
        }
    }

    private suspend fun parseImage(id: Long?, image: Image?) {
        image?.let {
            it.tvShowId = id
            AppDatabase.getInstance().imageDao().insert(image)
        }
    }

    private suspend fun getTvShowListDb(): List<TvShow> {
        //TODO: fix this shit with 'try catch'
        var list = Collections.emptyList<TvShow>()
        try {
            list = AppDatabase.getInstance().tvShowDao().getList()
        } catch (e: Exception) {
            Timber.e(e)
        }
        for (tvShow in list) {
            tvShow.id?.let { id ->
                try {
                    tvShow.rate = AppDatabase.getInstance().rateDao().getRateByTvShowId(tvShowId = id)
                } catch (e: Exception) {
                    Timber.e(e)
                }
                try {
                    tvShow.network = AppDatabase.getInstance().networkDao().getNetworkByTvShowId(tvShowId = id)
                } catch (e: Exception) {
                    Timber.e(e)
                }
                try {
                    tvShow.image = AppDatabase.getInstance().imageDao().getImageByTvShowId(tvShowId = id)
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
        return list
    }
}