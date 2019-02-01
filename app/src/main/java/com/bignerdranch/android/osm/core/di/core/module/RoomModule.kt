package com.bignerdranch.android.osm.core.di.core.module

import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.osm.core.data.AppDatabase
import com.bignerdranch.android.osm.core.domain.interactor.RoomService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    @Provides
    @Singleton
    fun roomService(db: AppDatabase): RoomService {
        return RoomService(db)
    }
}