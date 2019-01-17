package com.memebattle.template.core.di.sub.auth.module

import com.memebattle.template.features.auth.core.domain.interactor.AuthApiService
import com.memebattle.template.core.di.core.scope.FlowFragmentScope
import com.memebattle.template.features.auth.core.data.ApiAuth
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthApiModule {

    @FlowFragmentScope
    @Provides
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        val api = retrofit.create(ApiAuth::class.java)
        return AuthApiService(api)
    }
}