package com.example.todoist.project.di

import com.example.todoist.core.local.TodoistDatabase
import com.example.todoist.project.data.local.ProjectDao
import com.example.todoist.sync.data.network.SyncService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProjectModule {
    @Singleton
    @Provides
    fun provideProjectService(builder: Retrofit.Builder): SyncService {
        return builder
            .build()
            .create(SyncService::class.java)
    }

    @Provides
    @Singleton
    fun provideProjectDao(db: TodoistDatabase): ProjectDao {
        return db.projectDao
    }
}