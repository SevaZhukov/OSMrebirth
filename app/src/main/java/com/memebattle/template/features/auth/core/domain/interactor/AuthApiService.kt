package com.memebattle.template.features.auth.core.domain.interactor

import com.memebattle.template.core.domain.BaseCallback
import com.memebattle.template.features.auth.core.data.ApiAuth
import com.memebattle.template.features.auth.core.domain.model.req.UserReq
import com.memebattle.template.features.auth.core.domain.model.res.UserRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthApiService(var api: ApiAuth) {
    fun signIn(userReq: UserReq, callback: BaseCallback<UserRes>) {
        api.signIn(userReq).enqueue(object : Callback<UserRes> {
            override fun onResponse(call: Call<UserRes>, response: Response<UserRes>) {
                callback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<UserRes>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    fun signUp(userReq: UserReq, callback: BaseCallback<UserRes>) {
        api.signUp(userReq).enqueue(object : Callback<UserRes> {
            override fun onFailure(call: Call<UserRes>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<UserRes>, response: Response<UserRes>) {
                callback.onSuccess(response.body()!!)
            }

        })
    }
}