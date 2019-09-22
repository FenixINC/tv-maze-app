package com.example.tv_maze_app.data.network.services

import com.example.tv_maze_app.data.models.Cast
import com.example.tv_maze_app.data.models.Crew
import com.example.tv_maze_app.data.models.Episode
import com.example.tv_maze_app.data.models.TvShow
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TvShowService {

    @GET("/shows")
    fun getTvShowList(): Deferred<Response<List<TvShow>>>

    @GET("/shows/{id}/episodes")
    fun getEpisodeList(@Path("id") id: Long): Deferred<Response<List<Episode>>>

    @GET("/shows/{id}/cast")
    fun getTvShowCastList(@Path("id") id: Long): Deferred<Response<List<Cast>>>

    @GET("/shows/{id}/crew")
    fun getTvShowCrewList(@Path("id") id: Long): Deferred<Response<List<Crew>>>
}