package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.models.Crew

@Dao
interface CrewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Crew)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Crew>)

    @Delete
    suspend fun delete(obj: Crew)

    @Query("DELETE FROM tblCrew")
    suspend fun deleteAll()

    @Query("SELECT * FROM tblCrew")
    suspend fun getList(): List<Crew>
}