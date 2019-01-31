package com.memebattle.template.features.main.statistics

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memebattle.goldextensions.log
import com.memebattle.template.App
import com.memebattle.template.core.domain.interactor.RoomService
import com.memebattle.template.core.domain.model.Note
import org.eazegraph.lib.models.PieModel
import org.eazegraph.lib.models.ValueLinePoint
import org.eazegraph.lib.models.ValueLineSeries
import java.util.*
import javax.inject.Inject

class StatisticViewModel : ViewModel() {
    init {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var roomService: RoomService

    val pieSeriesLiveData = MutableLiveData<ArrayList<PieModel>>()
    val lineSeriesLiveData = MutableLiveData<ValueLineSeries>()

    fun getDataForChart(period: Int, moment: Int) {
        log("get data")
        val today = Date().time
        val beginPeriod = today - getLongInterval(period)
        val withPeriod = period != 0
        val withMoment = moment != 2
        var afterSleep = true
        when (moment) {
            0 -> afterSleep = true
            1 -> afterSleep = false
        }
        if (withPeriod) {
            if (withMoment)
                roomService.getIntervalFilterAll(beginPeriod, afterSleep, callback)
            else
                roomService.getIntervalFilterPeriod(beginPeriod, callback)
        } else {
            if (withMoment)
                roomService.getIntervalFilterMoment(afterSleep, callback)
            else
                roomService.getInterval(callback)
        }
    }

    private var callback = object : RoomService.NotesCallback {
        override fun onSuccess(notes: List<Note>) {
            log("notes $notes")
            val lineSeries = ValueLineSeries()
            val zones = Array(4) { 0 }
            lineSeries.color = Color.parseColor("#d7443c")
            lineSeries.widthOffset = 0.5f
            notes.forEach {
                val date = Date(it.date)
                val day = date.day
                val month = date.month
                val sDay: String
                val sMonth: String
                zones[it.zone - 1]++
                sDay = if (day < 10)
                    "0$day"
                else
                    "$day"
                sMonth = if (month < 10)
                    "0$month"
                else
                    "$month"
                lineSeries.addPoint(ValueLinePoint("$sDay.$sMonth", it.points.toFloat()))
            }
            val pieSeries: ArrayList<PieModel> = ArrayList()
            pieSeries.add(PieModel("I", zones[0].toFloat(), Color.parseColor("#c0e637")))
            pieSeries.add(PieModel("II", zones[1].toFloat(), Color.parseColor("#55bbeb")))
            pieSeries.add(PieModel("III", zones[2].toFloat(), Color.parseColor("#f0d73c")))
            pieSeries.add(PieModel("IV", zones[3].toFloat(), Color.parseColor("#d7443c")))

            pieSeriesLiveData.value = pieSeries
            lineSeriesLiveData.value = lineSeries
        }

        override fun onError(e: Throwable) {
            log("error ${e.message}")
        }

    }

    private fun getLongInterval(period: Int): Long {
        when (period) {
            0 -> return -1
            1 -> return 31536000000
            2 -> return 2592000000
            3 -> return 604800000
        }
        return 604800000
    }
}