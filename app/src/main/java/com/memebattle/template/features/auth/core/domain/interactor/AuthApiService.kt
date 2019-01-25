package com.memebattle.template.features.auth.core.domain.interactor

import com.memebattle.template.core.domain.BaseCallback
import com.memebattle.template.features.auth.core.domain.model.req.UserReq
import com.memebattle.template.features.auth.core.domain.model.res.UserRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthApiService {
    fun signIn(userReq: UserReq, callback: BaseCallback<UserRes>) {

    }

    fun signUp(userReq: UserReq, callback: BaseCallback<UserRes>) {

    }
}