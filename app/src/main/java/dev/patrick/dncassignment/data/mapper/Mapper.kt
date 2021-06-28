package dev.patrick.dncassignment.data.mapper

import dev.patrick.dncassignment.data.local.db.UserEntity
import dev.patrick.dncassignment.data.remote.api.UserDto
import dev.patrick.dncassignment.domain.model.User

fun mapUserEntityToDomain(
    input: UserEntity,
    isFavorite: Boolean = true
): User {
    return User(
        id = input.id,
        login = input.login,
        name = input.name,
        avatarUrl = input.avatarUrl,
        isFavorite = isFavorite
    )
}

fun mapUserResponseToDomain(
    input: UserDto,
    isFavorite: Boolean = false): User {
    return User(
        id = input.id,
        login = input.login,
        name = input.name,
        avatarUrl = input.avatarUrl,
        isFavorite = isFavorite
    )
}

fun mapUserDomainToEntity(input: User): UserEntity {
    return UserEntity(
        id = input.id,
        login = input.login,
        name = input.name,
        avatarUrl = input.avatarUrl
    )
}