package com.example.tv_maze_app.presentation.listeners

import com.example.tv_maze_app.data.entities.Crew

interface TvShowCrewListener {
    fun onItemClick(crew: Crew)
}