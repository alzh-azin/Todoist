package com.example.todoist.authentication.data.repository

import android.util.Log
import com.example.todoist.authentication.data.local.AuthenticationLocalDataSource
import com.example.todoist.authentication.data.network.AuthenticationRemoteDataSource
import com.example.todoist.core.network.utils.ConnectivityObserver
import com.example.todoist.core.network.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepository @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val authenticationLocalDataSource: AuthenticationLocalDataSource,
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource
) {

    fun isUserLoggedIn() = authenticationLocalDataSource.isUserLoggedIn()

    fun getToken(code: String): Flow<NetworkResult<Unit>> = flow {

        emit(NetworkResult.Loading(true))

        if (connectivityObserver.isConnected()) {

            when (val response = authenticationRemoteDataSource.getToken(code)) {

                is NetworkResult.Success -> {
                    Log.d("NetworkTest", "getToken: ${response.data?.accessToken} ")
                    authenticationLocalDataSource.setToken(response.data?.accessToken.orEmpty())
                    emit(NetworkResult.Success(Unit))
                }

                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(errorMessage = "Something went wrong, please try again"))
                }

                is NetworkResult.Exception -> {
                    emit(NetworkResult.Exception("Network request failed"))
                }

                else -> {}
            }

        } else {
            emit(NetworkResult.Error(errorMessage = "No internet connection"))
        }
    }

}