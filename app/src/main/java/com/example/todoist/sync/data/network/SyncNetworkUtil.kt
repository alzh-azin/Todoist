package com.example.todoist.sync.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

fun List<String>.toJsonString(): String {
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(List::class.java, String::class.java)
    val adapter = moshi.adapter<List<String>>(type)
    return adapter.toJson(this)
}