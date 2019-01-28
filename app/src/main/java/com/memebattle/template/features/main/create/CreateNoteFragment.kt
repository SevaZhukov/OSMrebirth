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
import kotlinx.android.synthetic.main.fragment_create_note.view.*
import java.util.*

class CreateNoteFragment : Fragment() {
    var afterSleep = false
    lateinit var pulseSitting: String
    lateinit var pulseStanding: String

    private lateinit var viewModel: CreateNoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_note, container, false)
        view.addNote.setOnClickListener {
            onGetResultClick()
        }
        view.sleepImage.setOnClickListener {
            onSleepClick()
        }
        view.trainImage.setOnClickListener {
            onTrainClick()
        }
        view.gotoWatch.setOnClickListener {
            onWatchClick()
        }
        if (afterSleep) {
            view.sleepImage.setImageResource(R.drawable.ic_alarm_red)
            view.trainImage.setImageResource(R.drawable.ic_training_grey)
        } else {
            view.sleepImage.setImageResource(R.drawable.ic_alarm_grey)
            view.trainImage.setImageResource(R.drawable.ic_training_red)
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
        pulseSitting = view!!.pulseSittingField.text.toString()
        pulseStanding = view!!.pulseStandingField.text.toString()
        viewModel.getResult(pulseSitting, pulseStanding)

    }

    fun setRes(points: Double, zone: Int) {
        val date = Date()
        val note = Note(pulseSitting, pulseStanding, date.time, points, zone, afterSleep)
        viewModel.addNote(note)
    }

    private fun gotoResult(note: Note) {
        //router.replaceTopController(RouterTransaction.with(ResultNoteController(note)))
        fragmentManager!!.beginTransaction()
                .replace(R.id.activityContainer, ResultNoteFragment())
                .commit()
    }

    private fun onWatchClick() {
        //router.pushController(RouterTransaction.with(ListController()))
    }

    fun onSleepClick() {
        afterSleep = true
        view!!.sleepImage.setImageResource(R.drawable.ic_alarm_red)
        view!!.trainImage.setImageResource(R.drawable.ic_training_grey)
    }

    fun onTrainClick() {
        afterSleep = false
        view!!.sleepImage.setImageResource(R.drawable.ic_alarm_grey)
        view!!.trainImage.setImageResource(R.drawable.ic_training_red)
    }
}