package com.memebattle.template.features.main.notes.paging

import androidx.paging.PositionalDataSource
import com.memebattle.template.App
import com.memebattle.template.core.domain.interactor.RoomService
import com.memebattle.template.core.domain.model.Note
import javax.inject.Inject

class NotePositionalDataSource() : PositionalDataSource<Note>() {
    var type = 0
    var afterSleep = false
    var beginPeriod: Long = 0

    constructor(afterSleep: Boolean) : this() {
        type = 1
        this.afterSleep = afterSleep
    }

    constructor(beginPeriod: Long) : this() {
        type = 2
        this.beginPeriod = beginPeriod
    }

    constructor(beginPeriod: Long, afterSleep: Boolean) : this() {
        type = 3
        this.afterSleep = afterSleep
        this.beginPeriod = beginPeriod
    }

    init {
        App.instance.daggerComponentHelper.mainComponent.inject(this)
    }

    @Inject
    lateinit var db: RoomService

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Note>) {
        when(type) {
            0 -> db.getRange(params, callback)
            1 -> db.getRangeFilterMoment(afterSleep, params, callback)
            2 -> db.getRangeFilterPeriod(beginPeriod, params, callback)
            3 -> db.getRangeFilterAll(beginPeriod, afterSleep, params, callback)
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Note>) {
        when(type) {
            0 -> db.getFirstPage(params, callback)
            1 -> db.getFirstPageFilterMoment(afterSleep, params, callback)
            2 -> db.getFirstPageFilterPeriod(beginPeriod, params, callback)
            3 -> db.getFirstPageFilterAll(beginPeriod, afterSleep, params, callback)
        }
    }
}