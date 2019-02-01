package com.bignerdranch.android.osm.features.main.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.core.domain.interactor.RoomService
import com.bignerdranch.android.osm.core.domain.model.Note
import com.bignerdranch.android.osm.core.domain.util.BaseCallback
import javax.inject.Inject

class ResultNoteViewModel : ViewModel() {

    val deleteStatus = MutableLiveData<String>()

    init {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var roomService: RoomService

    fun deleteNote(note: Note) {
        roomService.deleteNote(note, object : BaseCallback<String> {
            override fun onSuccess(data: String?) {
                deleteStatus.value = "success"
            }

            override fun onError(t: Throwable) {
                deleteStatus.value = "error"
            }
        })
    }
}