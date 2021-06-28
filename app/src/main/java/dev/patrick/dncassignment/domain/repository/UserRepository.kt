package dev.patrick.dncassignment.domain.repository

import dev.patrick.dncassignment.base.Resource
import dev.patrick.dncassignment.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    /**
     * query를 입력으로 유저의 목록 Flow를 반환
     */
    suspend fun getUsers(query: String, isOnlyFavorite: Boolean = false): Flow<Resource<List<User>>>

    /**
     * User 의 즐겨찾기 여부를 반환
     */
    suspend fun isFavorite(user: User): Flow<Resource<Boolean>>

    /**
     * Favorite 목록에 User 추가
     */
    suspend fun insertFavorite(user: User)

    /**
     * Favorite 목록에서 User 제거
     */
    suspend fun removeFavorite(user: User)
}