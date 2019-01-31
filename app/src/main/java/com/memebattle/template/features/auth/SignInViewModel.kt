package com.memebattle.template.features.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    var user = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    fun signIn(name: String) {

    }
}