package com.memebattle.template.features.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    var user = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    fun signIn(name: String) {

    }
}