package com.example.todoist.sync.data.network

enum class SyncResourceTypeHelper(val value: String) {

    projects("projects"),
    tasks("items"),
}

const val DEFAULT_SYNC_TOKEN = "*"