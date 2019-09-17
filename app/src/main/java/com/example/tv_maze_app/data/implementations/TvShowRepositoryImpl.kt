package com.example.tv_maze_app.data.implementations

import androidx.lifecycle.MutableLiveData
import com.example.tv_maze_app.data.db.AppDatabase
import com.example.tv_maze_app.data.models.ResultState
import com.example.tv_maze_app.data.models.TvShow
import com.example.tv_maze_app.data.network.ServiceGenerator
import com.example.tv_maze_app.data.network.services.TvShowService
import com.example.tv_maze_app.domain.repositories.TvShowRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class TvShowRepositoryImpl : TvShowRepository {

    private var mMutableLiveData = MutableLiveData<ResultState<List<TvShow>>>()

    private val mService by lazy {
        ServiceGenerator.createService(TvShowService::class.java)
    }

    override fun doLoadTvShowList(scope: CoroutineScope): MutableLiveData<ResultState<List<TvShow>>> {
        scope.launch(Dispatchers.Main) {
            try {
                val list = AppDatabase.getInstance().tvShowDao().getList()
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
                            AppDatabase.getInstance().tvShowDao().deleteAll()
                            AppDatabase.getInstance().tvShowDao().insert(list)
                            mMutableLiveData.postValue(ResultState.Success(list))
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

    private fun parseRate() {

    }

    private fun parseNetwork() {

    }

    private fun parseImage() {

    }
}