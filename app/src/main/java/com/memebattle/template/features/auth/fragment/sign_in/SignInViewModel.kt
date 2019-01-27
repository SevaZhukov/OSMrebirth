package com.memebattle.template.features.auth.fragment.sign_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memebattle.goldextensions.log
import com.memebattle.template.App
import com.memebattle.template.core.domain.util.BaseCallback
import com.memebattle.template.features.auth.core.domain.model.res.UserRes
import com.memebattle.template.features.auth.core.domain.interactor.AuthApiService
import com.memebattle.template.features.auth.core.domain.model.req.UserReq
import com.memebattle.template.features.auth.core.domain.interactor.AuthSettingsService
import javax.inject.Inject

class SignInViewModel : ViewModel() {

    var user = MutableLiveData<UserRes>()
    var error = MutableLiveData<String>()

    @Inject
    lateinit var apiService: AuthApiService
    @Inject
    lateinit var settingsService: AuthSettingsService

    init {
        App.instance.daggerComponentHelper.authComponent!!.inject(this)
    }

    fun signIn(name: String) {
        val userReq = UserReq(name)
        apiService.signIn(userReq, object : BaseCallback<UserRes> {
            override fun onSuccess(data: UserRes?) {
                log("success $data")
                user.value = data
                settingsService.setId("2")
            }

            override fun onError(t: Throwable) {
                log("error ${t.message}")
                user.value = UserRes("", "")
            }
        })
    }
}