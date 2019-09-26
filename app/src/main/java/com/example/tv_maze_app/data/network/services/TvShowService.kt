package com.example.tv_maze_app.data.network.services

import com.example.tv_maze_app.data.entities.Cast
import com.example.tv_maze_app.data.entities.Crew
import com.example.tv_maze_app.data.entities.Episode
import com.example.tv_maze_app.data.entities.TvShow
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