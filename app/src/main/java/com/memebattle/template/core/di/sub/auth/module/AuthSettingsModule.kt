package com.memebattle.template.core.di.sub.auth.module

import android.content.SharedPreferences
import com.memebattle.template.core.di.core.scope.FlowFragmentScope
import com.memebattle.template.features.auth.core.domain.interactor.AuthSettingsService
import dagger.Module
import dagger.Provides

@Module
class AuthSettingsModule {
    @FlowFragmentScope
    @Provides
    fun provideAuthSettingsService(sharedPreferences: SharedPreferences): AuthSettingsService {
        return AuthSettingsService(sharedPreferences)
    }
}