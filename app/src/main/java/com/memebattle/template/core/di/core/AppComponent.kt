package com.memebattle.template.core.di.core

import com.memebattle.template.core.di.core.module.RoomModule
import com.memebattle.template.core.di.core.module.AppModule
import com.memebattle.template.core.di.core.module.SharedPreferencesModule
import com.memebattle.template.features.main.create.CreateNoteViewModel
import com.memebattle.template.features.main.notes.paging.NotePositionalDataSource
import com.memebattle.template.features.main.settings.SettingsViewModel
import com.memebattle.template.features.main.statistics.StatisticViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, SharedPreferencesModule::class, RoomModule::class])
interface AppComponent {
    fun inject(notePositionalDataSource: NotePositionalDataSource)
    fun inject(statisticViewModel: StatisticViewModel)
    fun inject(createNoteViewModel: CreateNoteViewModel)
    fun inject(settingsViewModel: SettingsViewModel)
}