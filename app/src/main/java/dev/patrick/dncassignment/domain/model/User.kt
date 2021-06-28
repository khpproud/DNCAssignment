package dev.patrick.dncassignment.domain.model

/**
 * Domain Layer User 클래스
 * @param id: Unique User Id
 * @param login: Github User login id
 * @param name: Github User real name
 * @param avatarUrl: Profile image url
 * @param isFavorite: Whether favorite or not
 */
data class User(
    val id: Long,
    val login: String,
    val name: String,
    val avatarUrl: String,
    var isFavorite: Boolean
)