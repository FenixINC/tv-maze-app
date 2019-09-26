package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.entities.Embedded

@Dao
interface EmbeddedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Embedded)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Embedded>)

    @Delete
    suspend fun delete(obj: Embedded)

    @Query("DELETE FROM tblEmbedded")
    suspend fun deleteAll()

    @Query("SELECT * FROM tblEmbedded")
    suspend fun getList(): List<Embedded>
}