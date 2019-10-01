package com.example.tv_maze_app.domain.repositories

import androidx.lifecycle.MutableLiveData
import com.example.tv_maze_app.data.entities.ResultState
import com.example.tv_maze_app.data.entities.TvShow
import kotlinx.coroutines.CoroutineScope

interface TvShowRepository {
    fun doLoadTvShowList(scope: CoroutineScope): MutableLiveData<ResultState<List<TvShow>>>
    fun doRefreshTvShowList(scope: CoroutineScope): MutableLiveData<ResultState<List<TvShow>>>
    fun doFavorite(scope: CoroutineScope, id: Long?): MutableLiveData<ResultState<List<TvShow>>>
}