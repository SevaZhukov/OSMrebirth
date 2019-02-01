package com.bignerdranch.android.osm.features.main.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.core.domain.interactor.RoomService
import com.bignerdranch.android.osm.core.domain.util.BaseCallback
import javax.inject.Inject

class SettingsViewModel : ViewModel() {

    val deleteStatus = MutableLiveData<String>()

    init {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var roomService: RoomService

    fun deleteAllNotes() {
        roomService.deleteAll(object : BaseCallback<String> {
            override fun onSuccess(data: String?) {
                deleteStatus.value = "success"
            }

            override fun onError(t: Throwable) {
                deleteStatus.value = "error"
            }
        })
    }
}