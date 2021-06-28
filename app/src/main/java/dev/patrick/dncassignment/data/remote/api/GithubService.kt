package dev.patrick.dncassignment.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String = "",
        @Query("page") page: Int = PAGE,
        @Query("per_page") perPage: Int = PER_PAGE
    ): SearchResponse

    @GET("users/{user}")
    suspend fun getUserInfo(
        @Path("user") user: String
    ): UserDto

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val PAGE = 1
        const val PER_PAGE = 100
        const val QUALIFIER = "in:nametype:user"
    }
}