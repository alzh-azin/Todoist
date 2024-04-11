package com.example.todoist.authentication.data.network

import com.example.todoist.core.network.utils.NetworkResult
import com.example.todoist.core.network.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRemoteDataSource @Inject constructor(
    private val authenticationService: AuthenticationService
) {

    suspend fun getToken(code: String): NetworkResult<AuthenticationResponse> {
        return safeApiCall(
            { authenticationService.getToken(code = code) },
            "Error getting token"
        )
    }
}