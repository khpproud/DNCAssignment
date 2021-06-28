package dev.patrick.dncassignment.data.remote.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    val id: Long,
    val login: String,
    val name: String,
    @Json(name = "avatar_url")
    val avatarUrl: String = "",
)