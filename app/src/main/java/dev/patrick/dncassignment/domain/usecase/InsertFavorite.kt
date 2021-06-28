package dev.patrick.dncassignment.domain.usecase

import dev.patrick.dncassignment.domain.model.User
import dev.patrick.dncassignment.domain.repository.UserRepository
import javax.inject.Inject

class InsertFavorite @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(user: User) = repo.insertFavorite(user)
}