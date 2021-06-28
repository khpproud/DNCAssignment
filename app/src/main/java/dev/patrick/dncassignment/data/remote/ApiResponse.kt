package dev.patrick.dncassignment.data.remote

sealed class ApiResponse<out T> {
    object Empty : ApiResponse<Nothing>()
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMsg: String) : ApiResponse<Nothing>()
}