package com.bignerdranch.android.osm.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bignerdranch.android.osm.core.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}