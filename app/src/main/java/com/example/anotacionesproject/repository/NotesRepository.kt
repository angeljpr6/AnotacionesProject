package com.example.anotacionesproject.repository

import com.example.anotacionesproject.model.Notes
import com.example.anotacionesproject.room.NotesDataBaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotesRepository @Inject constructor(private val cronosDatabaseDao: NotesDataBaseDao) {
    suspend fun addNote(note: Notes)=cronosDatabaseDao.insert(note)
    suspend fun updateNote(note: Notes)=cronosDatabaseDao.update(note)
    suspend fun deleteNote(note: Notes)=cronosDatabaseDao.delete(note)
    fun getAllNotes(): Flow<List<Notes>> = cronosDatabaseDao.getCronos().flowOn(Dispatchers.IO).conflate()
    fun getNoteById(id:Long): Flow<Notes> = cronosDatabaseDao.getCronosById(id).flowOn(Dispatchers.IO).conflate()

}