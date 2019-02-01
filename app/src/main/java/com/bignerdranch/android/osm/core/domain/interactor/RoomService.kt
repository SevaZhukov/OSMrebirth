package com.bignerdranch.android.osm.core.domain.interactor

import androidx.paging.PositionalDataSource
import com.bignerdranch.android.osm.core.data.AppDatabase
import com.bignerdranch.android.osm.core.domain.model.Note
import com.bignerdranch.android.osm.core.domain.util.BaseCallback
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RoomService(val db: AppDatabase) {

    private val dao = db.noteDao()

    fun getInterval(callback: NotesCallback) {
        dao.getInterval()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteCallback(callback))
    }

    fun getIntervalFilterMoment(afterSleep: Boolean, callback: NotesCallback) {
        dao.getIntervalFilterMoment(afterSleep)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteCallback(callback))
    }

    fun getIntervalFilterPeriod(beginPeriod: Long, callback: NotesCallback) {
        dao.getIntervalFilterPeriod(beginPeriod)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteCallback(callback))
    }

    fun getIntervalFilterAll(beginPeriod: Long, afterSleep: Boolean, callback: NotesCallback) {
        dao.getIntervalFilterAll(beginPeriod, afterSleep)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteCallback(callback))
    }

    fun addNote(note: Note, callback: BaseCallback<String>) {
        Observable.create(ObservableOnSubscribe<Any> {
            dao.insertNote(note)
            it.onNext(note)
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    callback.onSuccess("")
                }
    }

    fun deleteAll(callback: BaseCallback<String>) {
        Observable.create(ObservableOnSubscribe<Any> {
            dao.deleteAllNotes()
            it.onNext("")
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    callback.onSuccess("") }
    }

    fun deleteNote(note: Note, callback: BaseCallback<String>) {
        Observable.create(ObservableOnSubscribe<Any> {
            dao.deleteNote(note.id)
            it.onNext("")
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    callback.onSuccess("") }
    }

    fun getRange(params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Note>) {
        dao.getPage(params.startPosition, params.loadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNotePageCallback(callback))
    }

    fun getRangeFilterMoment(afterSleep: Boolean, params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Note>) {
        dao.getPageFilterMoment(afterSleep, params.startPosition, params.loadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNotePageCallback(callback))
    }

    fun getRangeFilterPeriod(beginPeriod: Long, params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Note>) {
        dao.getPageFilterPeriod(beginPeriod, params.startPosition, params.loadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNotePageCallback(callback))
    }

    fun getRangeFilterAll(beginPeriod: Long, afterSleep: Boolean, params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Note>) {
        dao.getPageFilterAll(beginPeriod, afterSleep, params.startPosition, params.loadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNotePageCallback(callback))
    }

    fun getFirstPage(params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Note>) {
        dao.getFirstPage(params.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteFirstPageCallback(callback))
    }

    fun getFirstPageFilterMoment(afterSleep: Boolean, params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Note>) {
        dao.getFirstPageFilterMoment(afterSleep, params.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteFirstPageCallback(callback))
    }

    fun getFirstPageFilterPeriod(beginPeriod: Long, params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Note>) {
        dao.getFirstPageFilterPeriod(beginPeriod, params.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteFirstPageCallback(callback))
    }

    fun getFirstPageFilterAll(beginPeriod: Long, afterSleep: Boolean, params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Note>) {
        dao.getFirstPageFilterAll(beginPeriod, afterSleep, params.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteFirstPageCallback(callback))
    }

    fun getDataBaseFile() {

    }

    interface NotesCallback {
        fun onSuccess(notes: List<Note>)
        fun onError(e: Throwable)
    }

    interface EditNoteCallback {
        fun onSuccess()
        fun onError(e: Throwable)
    }
}