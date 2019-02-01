package com.bignerdranch.android.osm.features.main.notes.paging

import androidx.recyclerview.widget.DiffUtil
import com.bignerdranch.android.osm.core.domain.model.Note


class NoteDiffUtilCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.points == newItem.points && oldItem.afterSleep == newItem.afterSleep
    }
}