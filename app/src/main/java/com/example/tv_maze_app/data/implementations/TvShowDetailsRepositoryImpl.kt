package com.example.tv_maze_app.data.implementations

import androidx.lifecycle.MutableLiveData
import com.example.tv_maze_app.data.models.Cast
import com.example.tv_maze_app.data.models.Crew
import com.example.tv_maze_app.data.models.Episode
import com.example.tv_maze_app.data.models.ResultState
import com.example.tv_maze_app.data.network.ServiceGenerator
import com.example.tv_maze_app.data.network.services.TvShowService
import com.example.tv_maze_app.domain.repositories.TvShowDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class TvShowDetailsRepositoryImpl : TvShowDetailsRepository {

    private var mMutableLiveDataEpisode = MutableLiveData<ResultState<List<Episode>>>()
    private var mMutableLiveDataCast = MutableLiveData<ResultState<List<Cast>>>()
    private var mMutableLiveDataCrew = MutableLiveData<ResultState<List<Crew>>>()

    private val mService by lazy {
        ServiceGenerator.createService(TvShowService::class.java)
    }

    override fun doLoadEpisodeList(scope: CoroutineScope, id: Long?): MutableLiveData<ResultState<List<Episode>>> {
        mMutableLiveDataEpisode.value = ResultState.Loading()

        scope.launch(Dispatchers.Main) {
            val request = mService.getEpisodeList(id = id ?: 0L) // TODO: fix magic number
            withContext(Dispatchers.IO) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        response.body()?.let { episodeList ->
                            mMutableLiveDataEpisode.postValue(ResultState.Success(episodeList))
                        }
                    } else {
                        Timber.d(response.message())
                        mMutableLiveDataEpisode.postValue(ResultState.Error(error = response.message()))
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                    mMutableLiveDataEpisode.postValue(ResultState.Failure(throwable = e))
                }
            }
        }
        return mMutableLiveDataEpisode
    }

    override fun doLoadCastList(scope: CoroutineScope, id: Long?): MutableLiveData<ResultState<List<Cast>>> {
        mMutableLiveDataCast.value = ResultState.Loading()

        scope.launch(Dispatchers.Main) {
            val request = mService.getTvShowCastList(id = id ?: 0L) // TODO: fix magic number
            withContext(Dispatchers.IO) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        response.body()?.let { castList ->
                            mMutableLiveDataCast.postValue(ResultState.Success(castList))
                        }
                    } else {
                        Timber.d(response.message())
                        mMutableLiveDataCast.postValue(ResultState.Error(error = response.message()))
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                    mMutableLiveDataCast.postValue(ResultState.Failure(throwable = e))
                }
            }
        }
        return mMutableLiveDataCast
    }

    override fun doLoadCrewList(scope: CoroutineScope, id: Long?): MutableLiveData<ResultState<List<Crew>>> {
        mMutableLiveDataCrew.value = ResultState.Loading()

        scope.launch(Dispatchers.Main) {
            val request = mService.getTvShowCrewList(id = id ?: 0L) // TODO: fix magic number
            withContext(Dispatchers.IO) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        response.body()?.let { crewList ->
                            mMutableLiveDataCrew.postValue(ResultState.Success(crewList))
                        }
                    } else {
                        Timber.d(response.message())
                        mMutableLiveDataCrew.postValue(ResultState.Error(error = response.message()))
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                    mMutableLiveDataCrew.postValue(ResultState.Failure(throwable = e))
                }
            }
        }
        return mMutableLiveDataCrew
    }
}