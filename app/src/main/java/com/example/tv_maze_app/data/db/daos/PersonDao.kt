package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.models.Person

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Person)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Person>)

    @Delete
    suspend fun delete(obj: Person)

    @Query("DELETE FROM tblPerson")
    suspend fun deleteAll()

    @Query("SELECT * FROM tblPerson")
    suspend fun getList(): List<Person>
}