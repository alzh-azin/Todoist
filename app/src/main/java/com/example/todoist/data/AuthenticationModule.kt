package com.example.todoist.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthenticationModule {

    @Singleton
    @Provides
    fun provideAuthenticationService(builder: Retrofit.Builder): AuthenticationService {
        return builder
            .build()
            .create(AuthenticationService::class.java)
    }
}