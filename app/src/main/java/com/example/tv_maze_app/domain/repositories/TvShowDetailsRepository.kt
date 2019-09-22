package com.example.tv_maze_app.domain.repositories

import androidx.lifecycle.MutableLiveData
import com.example.tv_maze_app.data.models.Cast
import com.example.tv_maze_app.data.models.Crew
import com.example.tv_maze_app.data.models.Episode
import com.example.tv_maze_app.data.models.ResultState
import kotlinx.coroutines.CoroutineScope

interface TvShowDetailsRepository {
    fun doLoadEpisodeList(scope: CoroutineScope, id: Long?): MutableLiveData<ResultState<List<Episode>>>
    fun doLoadCastList(scope: CoroutineScope, id: Long?): MutableLiveData<ResultState<List<Cast>>>
    fun doLoadCrewList(scope: CoroutineScope, id: Long?): MutableLiveData<ResultState<List<Crew>>>
}