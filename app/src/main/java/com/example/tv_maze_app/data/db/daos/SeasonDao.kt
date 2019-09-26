package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.entities.Season

@Dao
interface SeasonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Season)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Season>)

    @Delete
    suspend fun delete(obj: Season)

    @Query("DELETE FROM tblSeason")
    suspend fun deleteAll()

    @Query("SELECT * FROM tblSeason")
    suspend fun getList(): List<Season>
}