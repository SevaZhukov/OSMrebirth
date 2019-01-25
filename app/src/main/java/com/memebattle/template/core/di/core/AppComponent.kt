package com.memebattle.template.core.di.core

import com.memebattle.template.core.di.core.module.RoomModule
import com.memebattle.template.core.di.sub.auth.AuthComponent
import com.memebattle.template.core.di.sub.main.MainComponent
import com.memebattle.template.core.di.core.module.AppModule
import com.memebattle.template.core.di.core.module.SharedPreferencesModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, SharedPreferencesModule::class, RoomModule::class])
interface AppComponent {
    fun authComponentBuilder(): AuthComponent.Builder
    fun mainComponentBuilder(): MainComponent.Builder
}