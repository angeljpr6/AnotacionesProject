package com.example.anotacionesproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.anotacionesproject.model.Notes
import com.example.anotacionesproject.room.NotesDataBaseDao

@Database(entities = [Notes::class], version=1, exportSchema=false)
abstract class NotesDataBase: RoomDatabase() {
    abstract fun notesDao(): NotesDataBaseDao
}