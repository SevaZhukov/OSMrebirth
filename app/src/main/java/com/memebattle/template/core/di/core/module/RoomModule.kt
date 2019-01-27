package com.memebattle.template.core.di.core.module

import android.content.Context
import androidx.room.Room
import com.memebattle.template.core.data.AppDatabase
import com.memebattle.template.core.data.NoteDao
import com.memebattle.template.core.domain.interactor.RoomService
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