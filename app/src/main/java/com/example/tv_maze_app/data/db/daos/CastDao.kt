package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.entities.Cast

@Dao
interface CastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Cast)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Cast>)

    @Delete
    suspend fun delete(obj: Cast)

    @Query("DELETE FROM tblCast")
    suspend fun deleteAll()

    @Query("SELECT * FROM tblCast")
    suspend fun getList(): List<Cast>
}