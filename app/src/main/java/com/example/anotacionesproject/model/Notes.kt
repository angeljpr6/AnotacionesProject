package com.example.anotacionesproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name="body")
    val body: String
)
