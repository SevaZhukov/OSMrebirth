package com.bignerdranch.android.osm.features.main.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bignerdranch.android.osm.core.domain.MainThreadExecutor
import com.bignerdranch.android.osm.core.domain.model.Note
import com.bignerdranch.android.osm.features.main.notes.paging.NotePositionalDataSource
import java.util.*
import java.util.concurrent.Executors

class NotesViewModel : ViewModel() {
    var pagedList = MutableLiveData<PagedList<Note>>()

    fun setRecyclerData(currentPeriod: Int, moment: Int) {
        val today = Date().time
        val beginPeriod = today - getLongInterval(currentPeriod)
        val withPeriod = currentPeriod != 0
        val withMoment = moment != 2
        var afterSleep = true
        when (moment) {
            0 -> afterSleep = true
            1 -> afterSleep = false
        }
        lateinit var positionalDataSource: NotePositionalDataSource
        positionalDataSource = if (withPeriod) {
            if (withMoment)
                NotePositionalDataSource(beginPeriod, afterSleep)
            else
                NotePositionalDataSource(beginPeriod)
        } else {
            if (withMoment)
                NotePositionalDataSource(afterSleep)
            else
                NotePositionalDataSource()
        }

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(8)
                .build()
        val pagedList = PagedList.Builder(positionalDataSource, config)
                .setNotifyExecutor(MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
        this.pagedList.value = pagedList
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