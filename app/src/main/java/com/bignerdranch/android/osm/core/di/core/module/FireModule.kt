package com.bignerdranch.android.osm.core.di.core.module

import android.content.Context
import com.bignerdranch.android.osm.core.domain.interactor.FireService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FireModule {
    @Provides
    @Singleton
    fun provideFireService(context: Context): FireService {
        return FireService(context)
    }
}