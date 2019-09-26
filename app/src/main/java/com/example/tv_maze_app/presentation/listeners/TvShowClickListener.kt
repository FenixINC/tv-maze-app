package com.example.tv_maze_app.presentation.listeners

import com.example.tv_maze_app.data.entities.TvShow

interface TvShowClickListener {
    fun onItemClick(tvShow: TvShow)
    fun onFavoriteClick(tvShow: TvShow)
    fun onWebsiteClick(url: String)
}