package com.memebattle.template.features.main.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memebattle.template.App
import com.memebattle.template.core.domain.interactor.RoomService
import com.memebattle.template.core.domain.util.BaseCallback
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