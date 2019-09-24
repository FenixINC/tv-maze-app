package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.models.Image

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Image)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Image>)

    @Delete
    suspend fun delete(obj: Image)

    @Query("DELETE FROM tblImage")
    suspend fun deleteAll()

    @Query("SELECT * FROM tblImage")
    suspend fun getList(): List<Image>

    @Query("SELECT * FROM tblImage WHERE tv_show_id = :tvShowId")
    suspend fun getImageByTvShowId(tvShowId: Long): Image
}