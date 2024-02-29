package com.example.todoist.home.data

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthenticationService {

    @POST("https://todoist.com/oauth/access_token")
    suspend fun getToken(
        @Query("client_id") clientId: String = "766ef02efd6b42429cc7a69f3e35bd3a",
        @Query("client_secret") clientSecret: String = "8b018d723c1b42b7975bf2dbd7b2e523",
        @Query("code") code: String
    ): Response<AuthenticationResponse>

}