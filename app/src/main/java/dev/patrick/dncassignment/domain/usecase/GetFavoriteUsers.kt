package dev.patrick.dncassignment.domain.usecase

import dev.patrick.dncassignment.domain.repository.UserRepository
import javax.inject.Inject

class GetFavoriteUsers @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(query: String, isOnlyFavorite: Boolean = true) =
        repo.getUsers(query, isOnlyFavorite)
}