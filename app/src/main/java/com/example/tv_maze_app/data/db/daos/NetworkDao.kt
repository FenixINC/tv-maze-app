package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.models.Network

@Dao
interface NetworkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Network)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Network>)

    @Delete
    suspend fun delete(obj: Network)

    @Query("DELETE FROM tblNetwork")
    suspend fun deleteAll()

    @Query("DELETE FROM tblNetwork WHERE tv_show_id = :tvSHowId")
    suspend fun deleteByTvSHowId(tvSHowId: Long)

    @Query("SELECT * FROM tblNetwork")
    suspend fun getList(): List<Network>

    @Query("SELECT * FROM tblNetwork WHERE tv_show_id = :tvShowId")
    suspend fun getNetworkByTvShowId(tvShowId: Long): Network
}