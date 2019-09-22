package com.example.tv_maze_app.presentation.listeners

import com.example.tv_maze_app.data.models.Episode

interface EpisodeClickListener {
    fun onItemClick(episode: Episode)
}