package dev.patrick.dncassignment.data.remote

import dev.patrick.dncassignment.data.remote.api.SearchUserDto
import dev.patrick.dncassignment.data.remote.api.UserDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getUsers(query: String): Flow<ApiResponse<List<SearchUserDto>>>
    suspend fun getUserInfo(login: String): Flow<ApiResponse<UserDto>>
}