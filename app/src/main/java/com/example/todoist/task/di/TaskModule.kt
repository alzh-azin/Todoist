package com.example.todoist.task.di

import com.example.todoist.core.local.TodoistDatabase
import com.example.todoist.task.data.local.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {

    @Provides
    @Singleton
    fun provideTaskDao(database: TodoistDatabase): TaskDao {
        return database.taskDao
    }
}