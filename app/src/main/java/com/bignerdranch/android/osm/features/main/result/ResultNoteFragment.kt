package com.bignerdranch.android.osm.features.main.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.memebattle.template.R
import com.bignerdranch.android.osm.core.domain.model.Note
import com.bignerdranch.android.osm.core.domain.util.FormatNote
import com.bignerdranch.android.osm.core.presentation.getFromBundle
import com.bignerdranch.android.osm.features.main.settings.SettingsViewModel
import com.memebattle.goldextensions.snack
import kotlinx.android.synthetic.main.fragment_result_note.*
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*


class ResultNoteFragment : Fragment() {

    private lateinit var note: Note

    lateinit var viewModel: ResultNoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(activity!!, R.id.nav_host_global)
        note = getFromBundle("note", Note::class.java) as Note
        val formatNote = FormatNote.format(note)
        zone.text = formatNote.zone
        points.text = note.points.toString()
        viewModel = ViewModelProviders.of(this).get(ResultNoteViewModel::class.java)
        viewModel.deleteStatus.observe(this, Observer {
            if(it == "success")
                navController.popBackStack()
            else
                snack("Не удалось удалить записи")
        })
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
        deleteButton.setOnClickListener {
            viewModel.deleteNote(note)
        }
        shareButton.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            val date = Date(note.date)
            val df = SimpleDateFormat("dd-MM-yyyy")
            val moment = if(note.afterSleep)
                "После сна"
            else
                "После тренировки"
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Ортостатическая проба\n${df.format(date)}\n$moment\nЗона ${note.zone}\nБалл ${note.points}\nПульс сидя ${note.pulseSitting}\nПульс стоя ${note.pulseStanding}")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        backButton.setOnClickListener {
            navController.popBackStack()
        }
        pulseSitting.text = note.pulseSitting
        pulseStanding.text = note.pulseStanding
        moment.setImageResource(formatNote.moment)
    }
}