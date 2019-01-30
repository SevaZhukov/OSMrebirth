package com.memebattle.template.features.main.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memebattle.template.R
import com.memebattle.template.core.domain.model.Note
import com.memebattle.template.core.domain.util.FormatNote
import com.memebattle.template.core.presentation.getFromBundle
import kotlinx.android.synthetic.main.fragment_result_note.*

class ResultNoteFragment : Fragment() {

    private lateinit var note: Note

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_result_note, container, false)
        note = getFromBundle("note", Note::class.java) as Note
        val formatNote = FormatNote.format(note)
        zone.text = formatNote.zone
        points.text = note.points.toString()
        when (note.zone) {
            1 -> {
                zoneFirst.setBackgroundResource(R.color.grey_back)
                zoneTitle.setText(R.string.zone_1t)
                zoneText.setText(R.string.zone_1)
            }
            2 -> {
                zoneSecond.setBackgroundResource(R.color.grey_back)
                zoneTitle.setText(R.string.zone_2t)
                zoneText.setText(R.string.zone_2)
            }
            3 -> {
                zoneThird.setBackgroundResource(R.color.grey_back)
                zoneTitle.setText(R.string.zone_3t)
                zoneText.setText(R.string.zone_3)
            }
            4 -> {
                zoneFourth.setBackgroundResource(R.color.grey_back)
                zoneTitle.setText(R.string.zone_4t)
                zoneText.setText(R.string.zone_4)
            }
        }
        pulseSitting.text = note.pulseSitting
        pulseStanding.text = note.pulseStanding
        moment.setImageResource(formatNote.moment)
        return view
    }
}