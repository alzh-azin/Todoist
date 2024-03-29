package com.example.todoist.authentication.data.network

import com.squareup.moshi.Json

data class AuthenticationResponse(

    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "token_type")
    val tokenType: String
)
