package com.memebattle.template.features.sign_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memebattle.goldextensions.log
import com.memebattle.template.App
import com.memebattle.template.core.domain.util.BaseCallback
import javax.inject.Inject

class SignInViewModel : ViewModel() {

    var user = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    fun signIn(name: String) {

    }
}