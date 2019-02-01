package com.bignerdranch.android.osm.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(var pulseSitting: String, var pulseStanding: String, var date: Long, var points: Double, var zone: Int, var afterSleep: Boolean) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}