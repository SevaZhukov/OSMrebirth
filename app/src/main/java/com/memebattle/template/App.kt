package com.memebattle.template

import android.app.Application
import com.memebattle.template.core.di.helper.DaggerComponentHelper

class App : Application() {
    lateinit var daggerComponentHelper: DaggerComponentHelper

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val url = resources.getString(R.string.url)
        daggerComponentHelper = DaggerComponentHelper(url)
    }
}