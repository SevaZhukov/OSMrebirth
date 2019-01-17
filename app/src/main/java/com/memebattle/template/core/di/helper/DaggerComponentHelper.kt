package com.memebattle.template.core.di.helper

import com.memebattle.template.App
import com.memebattle.template.core.di.core.DaggerAppComponent
import com.memebattle.template.core.di.sub.auth.AuthComponent
import com.memebattle.template.core.di.sub.auth.module.AuthApiModule
import com.memebattle.template.core.di.sub.auth.module.AuthSettingsModule
import com.memebattle.template.core.di.sub.main.MainComponent
import com.memebattle.template.core.di.core.module.AppModule
import com.memebattle.template.core.di.core.module.RetrofitModule

class DaggerComponentHelper(url: String) {

    private val appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(App.instance))
            .retrofitModule(RetrofitModule(url))
            .build()

    var authComponent: AuthComponent? = null
    var mainComponent: MainComponent? = null

    fun plusAuthComponent() {
        if (authComponent == null)
            authComponent = appComponent.authComponentBuilder()
                    .apiModule(AuthApiModule())
                    .settingsModule(AuthSettingsModule())
                    .buid()
    }

    fun removeAuthComponent() {
        authComponent = null
    }

    fun plusMainComponent() {
        if (mainComponent == null)
            mainComponent = appComponent.mainComponentBuilder()
                    .buid()
    }

    fun removeMainComponent() {
        mainComponent = null
    }
}