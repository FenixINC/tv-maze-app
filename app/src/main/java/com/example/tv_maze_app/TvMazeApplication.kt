package com.example.tv_maze_app

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

class TvMazeApplication : Application() {

    companion object {
        lateinit var sInstance: TvMazeApplication
            private set

        val getAppInstance: TvMazeApplication
            get() = sInstance
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        Stetho.initializeWithDefaults(this@TvMazeApplication)
        Timber.plant(Timber.DebugTree())
    }
}