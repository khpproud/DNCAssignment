package dev.patrick.dncassignment.data.remote

import dev.patrick.dncassignment.base.AppDispatchers
import dev.patrick.dncassignment.data.remote.api.GithubService
import dev.patrick.dncassignment.data.remote.api.SearchUserDto
import dev.patrick.dncassignment.data.remote.api.UserDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.net.URLEncoder
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: GithubService,
    private val dispatchers: AppDispatchers
) : RemoteDataSource {
    override suspend fun getUsers(query: String): Flow<ApiResponse<List<SearchUserDto>>> =
        executeCallbackFlow {
            val response = apiService.searchUsers(combineQualifier(query))
            if (response.items.isNotEmpty()) {
                ApiResponse.Success(response.items)
            } else {
                ApiResponse.Empty
            }
        }.flowOn(dispatchers.io)


    override suspend fun getUserInfo(login: String): Flow<ApiResponse<UserDto>> =
        executeCallbackFlow {
            val response = apiService.getUserInfo(login)
            if (response.login.isNotEmpty()) {
                ApiResponse.Success(response)
            } else {
                ApiResponse.Empty
            }
        }.flowOn(dispatchers.io)


    private fun combineQualifier(query: String): String {
        return "$query${GithubService.QUALIFIER}"
    }

    inline fun <reified T : Any> executeCallbackFlow(
        crossinline block: suspend () -> ApiResponse<T>
    ): Flow<ApiResponse<T>> = callbackFlow {
        try {
            send(block())
        } catch (e: Exception) {
            send(ApiResponse.Error(e.message ?: "Network Error caused"))
        }
        awaitClose { cancel() }
    }
}