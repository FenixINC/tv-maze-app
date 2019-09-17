package com.example.tv_maze_app.data.network.services

import com.example.tv_maze_app.data.models.TvShow
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface TvShowService {

    @GET("/shows")
    fun getTvShowList(): Deferred<Response<List<TvShow>>>
}