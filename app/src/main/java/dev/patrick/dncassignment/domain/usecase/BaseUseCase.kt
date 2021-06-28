package dev.patrick.dncassignment.domain.usecase

import javax.inject.Inject

class BaseUseCase @Inject constructor(
    val getUsers: GetUsers,
    val getFavoriteUsers: GetFavoriteUsers,
    val insertFavorite: InsertFavorite,
    val isFavorite: IsFavorite,
    val removeFavorite: RemoveFavorite
)