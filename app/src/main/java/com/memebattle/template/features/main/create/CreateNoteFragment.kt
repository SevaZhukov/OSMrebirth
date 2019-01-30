package com.memebattle.template.features.main.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.memebattle.template.R
import com.memebattle.template.core.domain.model.Note
import com.memebattle.template.core.presentation.putObject
import com.memebattle.template.features.main.result.ResultNoteFragment
import kotlinx.android.synthetic.main.fragment_create_note.*
import java.util.*

class CreateNoteFragment : Fragment() {
    var afterSleep = false
    lateinit var pulseSitting: String
    lateinit var pulseStanding: String

    private lateinit var viewModel: CreateNoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_note, container, false)
        addNote.setOnClickListener {
            onGetResultClick()
        }
        sleepImage.setOnClickListener {
            onSleepClick()
        }
        trainImage.setOnClickListener {
            onTrainClick()
        }
        gotoWatch.setOnClickListener {
            onWatchClick()
        }
        if (afterSleep) {
            sleepImage.setImageResource(R.drawable.ic_alarm_red)
            trainImage.setImageResource(R.drawable.ic_training_grey)
        } else {
            sleepImage.setImageResource(R.drawable.ic_alarm_grey)
            trainImage.setImageResource(R.drawable.ic_training_red)
        }
        viewModel = ViewModelProviders.of(this).get(CreateNoteViewModel::class.java)
        viewModel.result.observe(this, Observer {
            setRes(it.first, it.second)
        })
        viewModel.successAddingNote.observe(this, Observer {
            gotoResult(it)
        })
        return view
    }

    private fun onGetResultClick() {
        pulseSitting = pulseSittingField.text.toString()
        pulseStanding = pulseStandingField.text.toString()
        viewModel.getResult(pulseSitting, pulseStanding)

    }

    private fun setRes(points: Double, zone: Int) {
        val date = Date()
        val note = Note(pulseSitting, pulseStanding, date.time, points, zone, afterSleep)
        viewModel.addNote(note)
    }

    private fun gotoResult(note: Note) {
        val fragment = ResultNoteFragment()
        fragment.putObject("note", note)
        fragmentManager!!.beginTransaction()
                .replace(R.id.activityContainer, fragment)
                .commit()
    }

    private fun onWatchClick() {

    }

    private fun onSleepClick() {
        afterSleep = true
        sleepImage.setImageResource(R.drawable.ic_alarm_red)
        trainImage.setImageResource(R.drawable.ic_training_grey)
    }

    private fun onTrainClick() {
        afterSleep = false
        sleepImage.setImageResource(R.drawable.ic_alarm_grey)
        trainImage.setImageResource(R.drawable.ic_training_red)
    }
}