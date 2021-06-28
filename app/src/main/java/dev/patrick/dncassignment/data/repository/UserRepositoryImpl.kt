package dev.patrick.dncassignment.data.repository

import dev.patrick.dncassignment.base.AppDispatchers
import dev.patrick.dncassignment.base.Resource
import dev.patrick.dncassignment.data.local.LocalDataSource
import dev.patrick.dncassignment.data.mapper.mapUserDomainToEntity
import dev.patrick.dncassignment.data.mapper.mapUserEntityToDomain
import dev.patrick.dncassignment.data.mapper.mapUserResponseToDomain
import dev.patrick.dncassignment.data.remote.ApiResponse
import dev.patrick.dncassignment.data.remote.RemoteDataSource
import dev.patrick.dncassignment.data.remote.api.SearchUserDto
import dev.patrick.dncassignment.data.remote.api.UserDto
import dev.patrick.dncassignment.domain.model.User
import dev.patrick.dncassignment.domain.repository.UserRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dispatchers: AppDispatchers
) : UserRepository {

    override suspend fun getUsers(
        query: String,
        isOnlyFavorite: Boolean
    ): Flow<Resource<List<User>>> {
        return if (isOnlyFavorite) getFavoriteUsers(query) else getUsers(query)
    }

    /**
     * 로컬 DB에서 즐겨찾기한 User를 찾아 Domain Type으로 매핑하여 반환
     */
    private fun getFavoriteUsers(query: String): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading)
        emitAll(localDataSource.getFavoriteUsers(query).map { results ->
            Resource.Success(results.map {
                mapUserEntityToDomain(it)
            })
        })
    }

    /**
     * API에서 검색한 User의 정보와 DB의 즐겨찾기 한 User의 정보로 favorite 결합후
     * Domain Type으로 매핑하여 반환
     */
    private suspend fun getUsers(query: String): Flow<Resource<List<User>>> = coroutineScope {
        channelFlow {
            send(Resource.Loading)
            remoteDataSource.getUsers(query).collectLatest {
                when (it) {
                    is ApiResponse.Success -> {
                        val searchUsers = it.data
                        val userDto = searchUsers.map { dto ->
                            withContext(dispatchers.io) {
                                getUserInfo(dto)
                            }
                        }
                        withContext(dispatchers.computation) {
                            val users = userDto.sortedBy { user -> user.name.uppercase(Locale.US) }
                            localDataSource.getFavoriteUsers(query).map { userEntities ->
                                Resource.Success(users.map { userDto ->
                                    if (userEntities.find { entity -> userDto.id == entity.id } != null) {
                                        mapUserResponseToDomain(userDto, true)
                                    } else {
                                        mapUserResponseToDomain(userDto, false)
                                    }
                                })
                            }.collect { data ->
                                send(data)
                            }
                        }
                    }
                    is ApiResponse.Empty -> {
                        send(Resource.Success(emptyList<User>()))
                    }
                    is ApiResponse.Error -> {
                        send(Resource.Failure(it.errorMsg))
                    }
                }
            }
            awaitClose { cancel() }
        }
    }

    private suspend fun getUserInfo(dto: SearchUserDto): UserDto {
        return when (val result = remoteDataSource.getUserInfo(dto.login).first()) {
            is ApiResponse.Success<UserDto> -> {
                result.data
            }
            else -> {
                UserDto(
                    id = dto.id,
                    login = dto.login,
                    name = dto.login,
                    avatarUrl = dto.avatarUrl
                )
            }
        }
    }

    override suspend fun isFavorite(user: User): Flow<Resource<Boolean>> = flow {
        emitAll(localDataSource.getFavoriteUser(user.id).map {
            Resource.Success(it != null)
        })
    }

    override suspend fun insertFavorite(user: User) {
        localDataSource.insertFavorite(mapUserDomainToEntity(user))
    }

    override suspend fun removeFavorite(user: User) {
        localDataSource.deleteFavorite(mapUserDomainToEntity(user))
    }
}