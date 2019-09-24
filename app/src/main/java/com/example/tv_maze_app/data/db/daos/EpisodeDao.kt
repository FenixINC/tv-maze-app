package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.models.Episode

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Episode)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Episode>)

    @Delete
    suspend fun delete(obj: Episode)

    @Query("DELETE FROM tblEpisode")
    suspend fun deleteAll()

    @Query("SELECT * FROM tblEpisode")
    suspend fun getList(): List<Episode>
}