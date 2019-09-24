package com.example.tv_maze_app.data.db.daos

import androidx.room.*
import com.example.tv_maze_app.data.models.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<Character>)

    @Delete
    suspend fun delete(obj: Character)

    @Query("DELETE FROM tblCharacter")
    suspend fun deleteAll()

    @Query("SELECT * FROM tblCharacter")
    suspend fun getList(): List<Character>
}