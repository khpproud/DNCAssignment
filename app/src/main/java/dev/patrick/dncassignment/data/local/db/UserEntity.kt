package dev.patrick.dncassignment.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.patrick.dncassignment.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Long,
    val login: String,
    val name: String,
    val avatarUrl: String = "",
)

//fun List<UserEntity>.asDomainModel(): List<User> = map {
//    User(
//        id = it.id,
//        login = it.login,
//        name = it.name,
//        avatarUrl = it.avatarUrl,
//        isFavorite = it.isFavorite
//    )
//}