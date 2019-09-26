package com.example.tv_maze_app.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tv_maze_app.TvMazeApplication
import com.example.tv_maze_app.data.db.daos.*
import com.example.tv_maze_app.data.entities.*

@Database(entities = [
    (Cast::class),
    (Character::class),
    (Country::class),
    (Crew::class),
    (Embedded::class),
    (Episode::class),
    (Genre::class),
    (Image::class),
    (Network::class),
    (Person::class),
    (Rate::class),
    (Season::class),
    (TvShow::class)
], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun castDao(): CastDao

    abstract fun characterDao(): CharacterDao

    abstract fun countryDao(): CountryDao

    abstract fun crewDao(): CrewDao

    abstract fun embeddedDao(): EmbeddedDao

    abstract fun episodeDao(): EpisodeDao

    abstract fun genreDao(): GenreDao

    abstract fun imageDao(): ImageDao

    abstract fun networkDao(): NetworkDao

    abstract fun personDao(): PersonDao

    abstract fun rateDao(): RateDao

    abstract fun seasonDao(): SeasonDao

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