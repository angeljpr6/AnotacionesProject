package com.example.anotacionesproject.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.anotacionesproject.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDataBaseDao {
    //Crud
    @Query("SELECT * FROM notes")
    fun getCronos(): Flow<List<Notes>>

    @Query("SELECT * FROM notes WHERE id=:id")
    fun getCronosById(id:Long): Flow<Notes>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insert(notes:Notes)

    @Update(onConflict=OnConflictStrategy.REPLACE)
    suspend fun update(notes:Notes)

    @Delete
    suspend fun delete (notes:Notes)
}