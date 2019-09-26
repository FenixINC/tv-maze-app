package com.example.tv_maze_app.domain.repositories

import androidx.lifecycle.MutableLiveData
import com.example.tv_maze_app.data.entities.Cast
import com.example.tv_maze_app.data.entities.Crew
import com.example.tv_maze_app.data.entities.Episode
import com.example.tv_maze_app.data.entities.ResultState
import kotlinx.coroutines.CoroutineScope

interface TvShowDetailsRepository {
    fun doLoadEpisodeList(scope: CoroutineScope, id: Long?): MutableLiveData<ResultState<List<Episode>>>
    fun doLoadCastList(scope: CoroutineScope, id: Long?): MutableLiveData<ResultState<List<Cast>>>
    fun doLoadCrewList(scope: CoroutineScope, id: Long?): MutableLiveData<ResultState<List<Crew>>>
}