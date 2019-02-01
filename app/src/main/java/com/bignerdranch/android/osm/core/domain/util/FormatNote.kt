package com.bignerdranch.android.osm.core.domain.util

import android.graphics.Color
import com.memebattle.template.R
import com.bignerdranch.android.osm.core.domain.model.Note
import java.text.SimpleDateFormat

class FormatNote {
    companion object {
        fun format(note: Note): FormatedNote {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy")
            val date = dateFormat.format(note.date)
            var zone = "I"
            var colorZone = Color.parseColor("#c0e637")
            when (note.zone) {
                1 -> {
                    zone = "I"
                }
                2 -> {
                    zone = "II"
                    colorZone = Color.parseColor("#55bbeb")
                }
                3 -> {
                    zone = "III"
                    colorZone = Color.parseColor("#f0d73c")
                }
                4 -> {
                    zone = "IV"
                    colorZone = Color.parseColor("#d7443c")
                }
            }
            var moment = R.drawable.ic_training_red
            if (note.afterSleep)
                moment = R.drawable.ic_alarm_red

            return FormatedNote(date, zone, moment, colorZone)
        }

        class FormatedNote(var date: String,var zone: String, var moment: Int, var colorZone: Int)
    }
}