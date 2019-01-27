package com.bignerdranch.android.osm.presentation.notes.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bignerdranch.android.osm.R

class NoteViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var points: TextView = v.findViewById(R.id.item_note_points)
    var zone: TextView = v.findViewById(R.id.item_note_zone)
    var date: TextView = v.findViewById(R.id.item_note_date)
    var moment: ImageView = v.findViewById(R.id.item_note_moment)
}