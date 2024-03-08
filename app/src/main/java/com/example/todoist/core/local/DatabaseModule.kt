package com.example.todoist.core.local

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTodoistDatabase(app: Application): TodoistDatabase {
        return Room.databaseBuilder(
            app,
            TodoistDatabase::class.java,
            "todoistdb.db"
        ).build()
    }
}