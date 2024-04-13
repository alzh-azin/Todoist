package com.example.todoist.sync.data.network

import com.example.todoist.project.data.network.ProjectNetwork
import com.example.todoist.sync.domain.model.Sync
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SyncNetwork(
    @Json(name = "full_sync")
    val fullSync: Boolean,
    val projects: List<ProjectNetwork>,
    @Json(name = "sync_token")
    val syncToken: String,
    @Json(name = "temp_id_mapping")
    val tempIdMapping: Map<String, String>
) {

    fun toSync() = Sync(
        projects = projects.map { projectNetwork ->
            projectNetwork.toProject()
        }
    )
}

