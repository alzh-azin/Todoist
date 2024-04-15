package com.example.todoist.sync.data.network

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SyncService {

    @FormUrlEncoded
    @POST("v9/sync")
    suspend fun sync(
        @Field("sync_token") syncToken: String = DEFAULT_SYNC_TOKEN,
        @Field("resource_types") resourceTypes: String
    ): Response<SyncNetwork>
}