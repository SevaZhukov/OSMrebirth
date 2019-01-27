package com.memebattle.template.core.domain.interactor

import android.util.Log
import androidx.paging.PositionalDataSource
import com.memebattle.template.core.domain.interactor.RoomService
import com.memebattle.template.core.domain.model.Note
import io.reactivex.observers.DisposableSingleObserver
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class CallBackFabric {
    companion object {
        fun getNoteCallback(callback: RoomService.NotesCallback): DisposableSingleObserver<List<Note>> {
            return object : DisposableSingleObserver<List<Note>>() {
                override fun onSuccess(notes: List<Note>) {
                    callback.onSuccess(notes)
                }

                override fun onError(e: Throwable) {
                    callback.onError(e)
                }
            }
        }

        fun getNotePageCallback(callback: PositionalDataSource.LoadRangeCallback<Note>): DisposableSingleObserver<List<Note>> {
            return object : DisposableSingleObserver<List<Note>>() {
                override fun onSuccess(notes: List<Note>) {
                    callback.onResult(notes)
                }

                override fun onError(e: Throwable) {

                }
            }
        }

        fun getNoteFirstPageCallback(callback: PositionalDataSource.LoadInitialCallback<Note>): DisposableSingleObserver<List<Note>> {
            return object : DisposableSingleObserver<List<Note>>() {
                override fun onSuccess(t: List<Note>) {
                    Log.i("code", "first load range ${t.size}")
                    callback.onResult(t, 0)
                }

                override fun onError(e: Throwable) {
                    Log.i("code", "first error ${e.message}")
                }
            }
        }

        fun getCallback(callback: RoomService.EditNoteCallback): Subscriber<String> {
            return object : Subscriber<String> {
                override fun onComplete() {
                    callback.onSuccess()
                }

                override fun onSubscribe(s: Subscription?) {
                }

                override fun onNext(t: String?) {
                }

                override fun onError(e: Throwable) {
                    callback.onError(e)
                }

            }
        }
    }
}