package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.entities.TvShow

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: TvShow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<TvShow>)

    @Delete
    suspend fun delete(obj: TvShow)

    @Query("DELETE FROM tblTvShow")
    suspend fun deleteAll()

    @Query("SELECT * FROM tblTvShow ORDER BY name ASC")
    suspend fun getList(): List<TvShow>
}