package dev.patrick.dncassignment.data.remote.api

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class SearchUserDto(
    val id: Long,
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String = "",
) : Parcelable