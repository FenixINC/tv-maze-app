package com.example.tv_maze_app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tv_maze_app.domain.repositories.TvShowRepository
import kotlinx.coroutines.CoroutineScope

class TvShowViewModel() : ViewModel() {

    private var mRepository: TvShowRepository? = null

    constructor(repository: TvShowRepository) : this() {
        mRepository = repository
    }

    fun loadTvShowList(scope: CoroutineScope) = mRepository?.doLoadTvShowList(scope)
    fun refreshTvShowList(scope: CoroutineScope) = mRepository?.doRefreshTvShowList(scope)

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: TvShowRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TvShowViewModel(repository) as T
        }
    }
}