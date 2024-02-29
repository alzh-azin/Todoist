package com.example.todoist.core.network.interceptor

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.todoist.authentication.data.local.AuthenticationLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        if (request.url.encodedPath == "access_token")
            return chain.proceed(request)

        //TODO change the place of USER_TOKEN_KEY
        val token = runBlocking {
            dataStore.data.first()
        }[AuthenticationLocalDataSource.USER_TOKEN_KEY]

        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}