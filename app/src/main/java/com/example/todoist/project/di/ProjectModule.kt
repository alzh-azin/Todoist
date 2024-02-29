package com.example.todoist.project.di

import com.example.todoist.project.data.network.ProjectService
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
    fun provideProjectService(builder: Retrofit.Builder): ProjectService {
        return builder
            .build()
            .create(ProjectService::class.java)
    }
}