package com.example.tv_maze_app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tv_maze_app.domain.repositories.TvShowDetailsRepository
import kotlinx.coroutines.CoroutineScope

class TvShowDetailsViewModel() : ViewModel() {

    private var mRepository: TvShowDetailsRepository? = null

    constructor(repository: TvShowDetailsRepository) : this() {
        mRepository = repository
    }

    fun loadTvShowEpisodeList(scope: CoroutineScope, id: Long?) = mRepository?.doLoadEpisodeList(scope, id)
    fun loadTvShowCastList(scope: CoroutineScope, id: Long?) = mRepository?.doLoadCastList(scope, id)
    fun loadTvShowCrewList(scope: CoroutineScope, id: Long?) = mRepository?.doLoadCrewList(scope, id)

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: TvShowDetailsRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TvShowDetailsViewModel(repository) as T
        }
    }
}