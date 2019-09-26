package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.entities.Genre

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Genre>)

    @Delete
    suspend fun delete(obj: Genre)

    @Query("DELETE FROM tblGenre")
    suspend fun deleteAll()

    @Query("SELECT * FROM tblGenre")
    suspend fun getList(): List<Genre>
}