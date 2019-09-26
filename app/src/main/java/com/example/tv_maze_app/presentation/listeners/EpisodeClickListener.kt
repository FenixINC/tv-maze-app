package com.example.tv_maze_app.presentation.listeners

import com.example.tv_maze_app.data.entities.Episode

interface EpisodeClickListener {
    fun onItemClick(episode: Episode)
}