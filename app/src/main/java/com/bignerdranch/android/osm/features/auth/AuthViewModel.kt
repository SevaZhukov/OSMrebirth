package com.bignerdranch.android.osm.features.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.core.domain.interactor.FireService
import com.google.firebase.storage.UploadTask
import com.memebattle.goldextensions.log
import javax.inject.Inject

class AuthViewModel : ViewModel() {

    var user = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    init {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var fireService: FireService

    fun isUserAuth(): Boolean {
        return fireService.isEnter()
    }

    fun signIn(email: String, password: String) {
        fireService.signIn(email, password, object : FireService.AuthCallBack {
            override fun onSuccess(success: Boolean) {
                user.value = email
            }

            override fun onError(e: Throwable) {
                error.value = e.message
            }
        })
    }

    fun signUp(email: String, password: String) {
        fireService.signUp(email, password, object : FireService.AuthCallBack {
            override fun onSuccess(success: Boolean) {
                user.value = email
            }

            override fun onError(e: Throwable) {
                error.value = e.message
            }
        })
    }

    fun upload() {
        fireService.uploadData(object : FireService.UploadStorageCallBack {
            override fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot) {
                log("upload success $taskSnapshot")
            }

            override fun onError(e: Throwable) {
                log("upload error ${e.message}")
            }
        })
    }
}