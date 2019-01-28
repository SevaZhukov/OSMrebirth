package com.memebattle.template.features.main.notes.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.memebattle.template.R
import com.memebattle.template.core.domain.model.Note
import com.memebattle.template.core.domain.util.FormatNote
import kotlinx.android.synthetic.main.item_note.view.*

class NotePagingAdapter(diffCallback: DiffUtil.ItemCallback<Note>) : PagedListAdapter<Note, NoteViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(v)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        val formatNote = FormatNote.format(note!!)
        holder.itemView.setOnClickListener {

        }
        holder.itemView.points.text = "${note.points}"
        holder.itemView.date.text = formatNote.date
        holder.itemView.zone.text = formatNote.zone
        holder.itemView.zone.setTextColor(formatNote.colorZone)
        holder.itemView.moment.setImageResource(formatNote.moment)
    }
}