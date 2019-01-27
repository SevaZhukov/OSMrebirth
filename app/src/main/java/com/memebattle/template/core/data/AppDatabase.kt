package com.memebattle.template.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.memebattle.template.core.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}