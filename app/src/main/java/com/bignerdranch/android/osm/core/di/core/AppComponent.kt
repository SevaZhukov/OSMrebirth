package com.bignerdranch.android.osm.core.di.core

import com.bignerdranch.android.osm.core.di.core.module.RoomModule
import com.bignerdranch.android.osm.core.di.core.module.AppModule
import com.bignerdranch.android.osm.core.di.core.module.SharedPreferencesModule
import com.bignerdranch.android.osm.features.main.create.CreateNoteViewModel
import com.bignerdranch.android.osm.features.main.notes.paging.NotePositionalDataSource
import com.bignerdranch.android.osm.features.main.result.ResultNoteViewModel
import com.bignerdranch.android.osm.features.main.settings.SettingsViewModel
import com.bignerdranch.android.osm.features.main.statistics.StatisticViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, SharedPreferencesModule::class, RoomModule::class])
interface AppComponent {
    fun inject(notePositionalDataSource: NotePositionalDataSource)
    fun inject(statisticViewModel: StatisticViewModel)
    fun inject(createNoteViewModel: CreateNoteViewModel)
    fun inject(settingsViewModel: SettingsViewModel)
    fun inject(resultNoteViewModel: ResultNoteViewModel)
}