package com.example.tv_maze_app.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tv_maze_app.TvMazeApplication
import com.example.tv_maze_app.data.db.daos.TvShowDao
import com.example.tv_maze_app.data.models.*

@Database(entities = [
    (TvShow::class),
    (Country::class),
    (Image::class),
    (Rate::class),
    (Genre::class)
], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tvShowDao(): TvShowDao

    companion object INSTANCE {
        private var sInstance: AppDatabase? = null

        @Synchronized
        private fun newInstance(): AppDatabase {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(TvMazeApplication.getAppInstance, AppDatabase::class.java, "TvMaze-Database")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return sInstance!!
        }

        @JvmStatic
        fun getInstance(): AppDatabase {
            if (sInstance == null) {
                newInstance()
            }
            return sInstance!!
        }
    }
}