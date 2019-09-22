package com.example.tv_maze_app.presentation.listeners

import com.example.tv_maze_app.data.models.Crew

interface TvShowCrewListener {
    fun onItemClick(crew: Crew)
}