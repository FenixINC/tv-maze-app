package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.models.Rate

@Dao
interface RateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Rate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Rate>)

    @Delete
    suspend fun delete(obj: Rate)

    @Query("DELETE FROM tblRate")
    suspend fun deleteAll()

    @Query("DELETE FROM tblRate WHERE tv_show_id = :tvShowId")
    suspend fun deleteByTvShowId(tvShowId: Long)

    @Query("SELECT * FROM tblRate")
    suspend fun getList(): List<Rate>

    @Query("SELECT * FROM tblRate WHERE tv_show_id = :tvShowId")
    suspend fun getRateByTvShowId(tvShowId: Long): Rate
}