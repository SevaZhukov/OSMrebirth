package com.memebattle.template.features.auth.fragment.sign_up

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memebattle.goldextensions.log
import com.memebattle.template.App
import com.memebattle.template.core.domain.util.BaseCallback
import com.memebattle.template.features.auth.core.domain.interactor.AuthApiService
import com.memebattle.template.features.auth.core.domain.model.req.UserReq
import com.memebattle.template.features.auth.core.domain.model.res.UserRes
import com.memebattle.template.features.auth.core.domain.interactor.AuthSettingsService
import javax.inject.Inject

class SignUpViewModel: ViewModel() {

    lateinit var user: MutableLiveData<UserRes>
    lateinit var error: MutableLiveData<String>

    @Inject
    lateinit var authApiService: AuthApiService
    @Inject
    lateinit var authSettingsService: AuthSettingsService

    init {
        App.instance.daggerComponentHelper.authComponent!!.inject(this)
    }

    fun signUp(name: String) {
        val userReq = UserReq(name)
        authApiService.signUp(userReq, object : BaseCallback<UserRes> {
            override fun onSuccess(data: UserRes?) {
                log("success $data")
                user.value = data
                authSettingsService.setId("2")
            }

            override fun onError(t: Throwable) {
                log("error ${t.message}")
                user.value = UserRes("", "")
            }
        })
    }
}