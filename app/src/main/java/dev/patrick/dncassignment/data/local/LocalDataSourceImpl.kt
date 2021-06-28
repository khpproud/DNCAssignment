package dev.patrick.dncassignment.data.local

import dev.patrick.dncassignment.data.local.db.UserDao
import dev.patrick.dncassignment.data.local.db.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : LocalDataSource {
    override fun getFavoriteUsers(query: String): Flow<List<UserEntity>> = userDao.searchUsers(query)

    override fun getFavoriteUser(id: Long): Flow<UserEntity?> = userDao.getUser(id)

    override suspend fun insertFavorite(user: UserEntity) = userDao.insertUser(user)

    override suspend fun deleteFavorite(user: UserEntity) = userDao.deleteUser(user)
}