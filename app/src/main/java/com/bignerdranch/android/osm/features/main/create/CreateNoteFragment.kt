package com.bignerdranch.android.osm.features.main.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.memebattle.goldextensions.log
import com.memebattle.template.R
import com.bignerdranch.android.osm.core.domain.model.Note
import com.bignerdranch.android.osm.core.presentation.createBundle
import kotlinx.android.synthetic.main.fragment_create_note.*
import java.util.*

class CreateNoteFragment : Fragment() {
    var afterSleep = false
    lateinit var pulseSitting: String
    lateinit var pulseStanding: String

    private lateinit var viewModel: CreateNoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_note, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CreateNoteViewModel::class.java)
        viewModel.result.observe(this, Observer {
            log("result")
            setRes(it.first, it.second)
        })
        viewModel.successAddingNote.observe(this, Observer {
            log("successAddingNote")
            gotoResult(it)
        })
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
        val args = createBundle("note", note)
        val navController = Navigation.findNavController(activity!!, R.id.nav_host_global)
        navController.navigate(R.id.action_createNoteFragment_to_resultNoteFragment, args)
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