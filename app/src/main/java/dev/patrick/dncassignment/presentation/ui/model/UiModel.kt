package dev.patrick.dncassignment.presentation.ui.model

import dev.patrick.dncassignment.domain.model.User

sealed class UiModel {
    data class UserItem(val user: User) : UiModel()
    data class HeaderItem(val initialChar: String) : UiModel()
}