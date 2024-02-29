package com.example.todoist.core.network.di

import com.example.todoist.core.network.utils.ConnectivityObserver
import com.example.todoist.core.network.utils.NetworkConnectivityObserver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class NetworkStatusModule {

    @Singleton
    @Binds
    abstract fun bindConnectivityObserver(networkConnectivityObserver: NetworkConnectivityObserver)
            : ConnectivityObserver
}