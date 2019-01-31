package com.memebattle.template

import android.app.Application
import com.memebattle.template.core.di.core.AppComponent
import com.memebattle.template.core.di.core.DaggerAppComponent
import com.memebattle.template.core.di.core.module.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(instance))
                .build()
    }
}