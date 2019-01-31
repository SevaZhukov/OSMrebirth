package com.memebattle.template.features.main.statistics

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.memebattle.template.R
import kotlinx.android.synthetic.main.fragment_statistic.*
import org.eazegraph.lib.models.PieModel
import org.eazegraph.lib.models.ValueLineSeries

class StatisticFragment : Fragment() {
    private var currentPeriod = 4
    private var moment = 2

    private lateinit var viewModel: StatisticViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_statistic, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StatisticViewModel::class.java)
        viewModel.lineSeriesLiveData.observe(this, Observer {
            setLineChartData(it)
        })
        viewModel.pieSeriesLiveData.observe(this, Observer {
            setPieChart(it)
        })
        updateData()
    }

    private fun setLineChartData(series: ValueLineSeries) {
        lineChart.clearChart()
        lineChart.addSeries(series)
        lineChart.startAnimation()
    }

    private fun setPieChart(pieSeries: ArrayList<PieModel>) {
        pieChart.clearChart()
        pieSeries.forEach {
            pieChart.addPieSlice(it)
        }
        pieChart.startAnimation()
    }

    private fun updateMoment(moment: Int) {
        sleepImage.setImageResource(R.drawable.ic_alarm_grey)
        trainImage.setImageResource(R.drawable.ic_training_grey)
        allImage.setImageResource(R.drawable.ic_moment_all_grey)
        when (moment) {
            0 -> sleepImage.setImageResource(R.drawable.ic_alarm_red)
            1 -> trainImage.setImageResource(R.drawable.ic_training_red)
            2 -> allImage.setImageResource(R.drawable.ic_moment_all_red)
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
        sleepImage.setOnClickListener {
            onSleepClick()
        }
        trainImage.setOnClickListener {
            onTrainClick()
        }
        allImage.setOnClickListener {
            onAllClick()
        }
    }

    private fun onPeriodAllClick() {
        if (periodAll.textSize != 42.0f) {
            currentPeriod = 0
            updateData()
        }
    }

    private fun onPeriodYearClick() {
        if (periodYear.textSize != 42.0f) {
            currentPeriod = 1
            updateData()
        }
    }

    private fun onPeriodMonthClick() {
        if (periodMonth.textSize != 42.0f) {
            currentPeriod = 2
            updateData()
        }
    }

    private fun onPeriodWeekClick() {
        if (periodWeek.textSize != 42.0f) {
            currentPeriod = 3
            updateData()
        }
    }

    private fun onTrainClick() {
        moment = 1
        updateData()
    }

    private fun onSleepClick() {
        moment = 0
        updateData()
    }

    private fun onAllClick() {
        moment = 2
        updateData()
    }

    @SuppressLint("ResourceAsColor")
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

    private fun updateData() {
        updateMoment(moment)
        updatePeriod(currentPeriod)
        if (currentPeriod == 4)
            viewModel.getDataForChart(currentPeriod - 1, moment)
        else
            viewModel.getDataForChart(currentPeriod, moment)
    }
}