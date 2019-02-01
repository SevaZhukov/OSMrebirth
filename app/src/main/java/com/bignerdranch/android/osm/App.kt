package com.bignerdranch.android.osm

import android.app.Application
import com.bignerdranch.android.osm.core.data.AppDatabase
import com.bignerdranch.android.osm.core.di.core.AppComponent
import com.bignerdranch.android.osm.core.di.core.DaggerAppComponent
import com.bignerdranch.android.osm.core.di.core.module.AppModule

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