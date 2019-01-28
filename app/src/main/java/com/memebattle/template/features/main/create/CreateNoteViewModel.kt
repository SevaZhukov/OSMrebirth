package com.memebattle.template.features.main.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memebattle.template.core.domain.interactor.RoomService
import com.memebattle.template.core.domain.model.Note
import com.memebattle.template.core.domain.util.BaseCallback
import javax.inject.Inject

class CreateNoteViewModel : ViewModel() {
    var result = MutableLiveData<Pair<Double, Int>>()
    var successAddingNote = MutableLiveData<Note>()

    @Inject
    lateinit var roomService: RoomService

    fun getResult(pulseSitting: String, pulseStanding: String) {
        val x = 2.27
        val y = 0.5
        var points = 14.5 - y * (pulseSitting.toFloat() - 40) / 3.5 - (pulseStanding.toFloat() - pulseSitting.toFloat()) / x * 0.5
        points *= 100
        val i = Math.round(points).toInt()
        points = i.toDouble() / 100
        val zone = getZone(points)
        result.value = Pair(points, zone)
    }

    private fun getZone(points: Double): Int {
        var n = 0
        if (points >= 7.5)
            n = 1
        if (points >= 5 && points < 7.5)
            n = 2
        if (points >= 2.5 && points < 5)
            n = 3
        if (points < 2.5)
            n = 4
        return n
    }

    fun addNote(note: Note) {
        roomService.addNote(note, object : BaseCallback<String> {
            override fun onSuccess(data: String?) {
                successAddingNote.value = note
            }

            override fun onError(t: Throwable) {
            }

        })
    }
}