package com.example.tv_maze_app.presentation.listeners

import com.example.tv_maze_app.data.entities.TvShow

interface TvShowClickListener {
    fun onItemClick(tvShow: TvShow)
    fun onFavoriteClick(tvShow: TvShow, position: Int)
    fun onWebsiteClick(url: String)
}