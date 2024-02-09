package com.example.todoist.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource
) {

    suspend fun getToken(code: String) = authenticationRemoteDataSource.getToken(code)

}