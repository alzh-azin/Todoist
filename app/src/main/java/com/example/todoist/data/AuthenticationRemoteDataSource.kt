package com.example.todoist.data

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