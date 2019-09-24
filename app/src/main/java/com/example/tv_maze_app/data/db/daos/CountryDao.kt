package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.models.Country

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Country)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Country>)

    @Delete
    suspend fun delete(obj: Country)

    @Query("DELETE FROM tblCountry")
    suspend fun deleteAll()

    @Query("SELECT * FROM tblCountry")
    suspend fun getList(): List<Country>
}