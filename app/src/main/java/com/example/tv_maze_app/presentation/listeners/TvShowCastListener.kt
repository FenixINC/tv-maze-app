package com.example.tv_maze_app.presentation.listeners

import com.example.tv_maze_app.data.entities.Cast

interface TvShowCastListener {
    fun onItemClick(cast: Cast)
}