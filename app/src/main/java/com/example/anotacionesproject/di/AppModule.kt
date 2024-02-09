package com.example.anotacionesproject.di

import android.content.Context
import androidx.room.Room
import com.example.anotacionesproject.room.NotesDataBase
import com.example.anotacionesproject.room.NotesDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesCronosDao(cronosDatabase:NotesDataBase): NotesDataBaseDao{
        return cronosDatabase.notesDao()
    }
    @Singleton
    @Provides
    fun providesCronosDatabase(@ApplicationContext context: Context): NotesDataBase{
        return Room.databaseBuilder(
            context,
            NotesDataBase:: class.java, "notes_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}