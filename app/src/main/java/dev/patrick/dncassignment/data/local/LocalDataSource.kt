package dev.patrick.dncassignment.data.local

import dev.patrick.dncassignment.data.local.db.UserEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getFavoriteUsers(query: String): Flow<List<UserEntity>>
    fun getFavoriteUser(id: Long): Flow<UserEntity?>
    suspend fun insertFavorite(user: UserEntity)
    suspend fun deleteFavorite(user: UserEntity)
}