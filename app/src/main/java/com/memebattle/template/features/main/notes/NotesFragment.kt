package com.memebattle.template.features.main.notes

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.osm.presentation.notes.recycler.NotePagingAdapter
import com.memebattle.template.App
import com.memebattle.template.R
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment() {

    var filter = false
    private var currentPeriod = 4
    private var moment = 2

    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.flow_fragment_main, container, false)
        notes_recycler.layoutManager = LinearLayoutManager(activity)
        App.instance.daggerComponentHelper.mainComponent!!.inject(this)
        notesFab.setOnClickListener {
            onFABClick()
        }
        periodAll.setOnClickListener {
            onPeriodAllClick()
        }
        periodYear.setOnClickListener {
            onPeriodYearClick()
        }
        periodMonth.setOnClickListener {
            onPeriodMonthClick()
        }
        periodWeek.setOnClickListener {
            onPeriodWeekClick()
        }
        notes_show_hide_filter.setOnClickListener {
            onShowFilterClick()
        }
        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        updateData()
        return v
    }

    fun onFABClick() {
        //router.pushController(RouterTransaction.with(AddNoteController()))
    }

    override fun setAdapter(pagedList: PagedList<Note>) {
        val adapter = NotePagingAdapter(NoteDiffUtilCallback(), router)
        adapter.submitList(pagedList)
        notes_recycler.adapter = adapter
    }

    private fun updateMoment(moment: Int) {
        momentSleep.setImageResource(R.drawable.ic_alarm_grey)
        momentTrain.setImageResource(R.drawable.ic_training_grey)
        momentAll.setImageResource(R.drawable.ic_moment_all_grey)
        when (moment) {
            0 -> momentSleep.setImageResource(R.drawable.ic_alarm_red)
            1 -> momentTrain.setImageResource(R.drawable.ic_training_red)
            2 -> momentAll.setImageResource(R.drawable.ic_moment_all_red)
        }
    }

    private fun updatePeriod(period: Int) {
        val grey = Color.parseColor("#848484")
        val red = Color.parseColor("#d7443c")
        periodAll.setTextColor(grey)
        periodYear.setTextColor(grey)
        periodMonth.setTextColor(grey)
        periodWeek.setTextColor(grey)
        periodAll.textSize = 18f
        periodYear.textSize = 18f
        periodMonth.textSize = 18f
        periodWeek.textSize = 18f
        when (period) {
            0 -> {
                periodAll.setTextColor(red)
                periodAll.textSize = 21f
            }
            1 -> {
                periodYear.setTextColor(red)
                periodYear.textSize = 21f
            }
            2 -> {
                periodMonth.setTextColor(red)
                periodMonth.textSize = 21f
            }
            3, 4 -> {
                periodWeek.setTextColor(red)
                periodWeek.textSize = 21f
            }
        }
    }

    //@OnClick(R.id.notes_period_all)
    fun onPeriodAllClick() {
        Log.i("code", periodAll.textSize.toString())
        if (periodAll.textSize != 42.0f) {
            currentPeriod = 0
            updateData()
        }
    }

    //@OnClick(R.id.notes_period_year)
    fun onPeriodYearClick() {
        if (periodYear.textSize != 42.0f) {
            currentPeriod = 1
            updateData()
        }
    }

    //@OnClick(R.id.notes_period_month)
    fun onPeriodMonthClick() {
        if (periodMonth.textSize != 42.0f) {
            currentPeriod = 2
            updateData()
        }
    }

    //@OnClick(R.id.notes_period_week)
    fun onPeriodWeekClick() {
        if (periodWeek.textSize != 42.0f) {
            currentPeriod = 3
            updateData()
        }
    }

    //@OnClick(R.id.notes_train_image)
    fun onTrainClick() {
        moment = 1
        updateData()
    }

    //@OnClick(R.id.notes_sleep_image)
    fun onSleepClick() {
        moment = 0
        updateData()
    }

    //@OnClick(R.id.notes_all_image)
    fun onAllClick() {
        moment = 2
        updateData()
    }

    private fun updateData() {
        updateMoment(moment)
        updatePeriod(currentPeriod)
        if (currentPeriod == 4)
            presenter.setRecyclerData(currentPeriod - 1, moment)
        else
            presenter.setRecyclerData(currentPeriod, moment)
    }

    //@OnClick(R.id.notes_show_hide_filter)
    fun onShowFilterClick() {
        if (filter) {
            notes_filter_layout.visibility = View.GONE
            notes_show_hide_image.setImageResource(R.drawable.ic_arrow_open_filter)
        }
        else {
            notes_filter_layout.visibility = View.VISIBLE
            notes_show_hide_image.setImageResource(R.drawable.ic_arrow_filter_close)
        }

        filter = !filter
    }
}