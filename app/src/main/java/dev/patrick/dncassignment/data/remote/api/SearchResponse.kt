package dev.patrick.dncassignment.data.remote.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "total_count")
    val totalCount: Long = 0,
    val items: List<SearchUserDto> = emptyList(),
    val message: String = "",
)