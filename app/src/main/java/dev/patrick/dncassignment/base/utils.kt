package dev.patrick.dncassignment.base

import dev.patrick.dncassignment.domain.model.User
import dev.patrick.dncassignment.presentation.ui.model.UiModel
import java.util.*

fun convertUiModel(users: List<User>): List<UiModel> {
    val uiModelList = mutableListOf<UiModel>()
    if (users.isNotEmpty()) {
        val first = getFirstChar(users[0])
        var isKor = isKorean(first)
        if (isKor) {
            uiModelList.add(UiModel.HeaderItem(getChoChar(first)))
        } else {
            uiModelList.add(UiModel.HeaderItem(first))
        }
        uiModelList.add(UiModel.UserItem(users[0]))
        users.reduce { before, now ->
            val beforeChar = getFirstChar(before)
            val nowChar = getFirstChar(now)
            if (beforeChar != nowChar) {
                isKor = isKorean(nowChar)
                if (isKor) {
                    val beforeKor = getChoChar(beforeChar)
                    val nowKor = getChoChar(nowChar)
                    if (beforeKor != nowKor) {
                        uiModelList.add(UiModel.HeaderItem(getChoChar(nowChar)))
                    }
                } else {
                    uiModelList.add(UiModel.HeaderItem(nowChar))
                }
            }
            uiModelList.add(UiModel.UserItem(now))
            now
        }
    }
    return uiModelList
}

private fun getFirstChar(user: User): String = user.name.uppercase(Locale.US).first().toString()

private fun isKorean(name: String): Boolean {
    val first = (name.first().toChar()-0xAC00).code

    // 한글일 경우
    return first in 0..11172
}

private fun getChoChar(name: String): String {
    val first = (name.first().toChar()-0xAC00).code
    return Char((((first - (first % 28)) / 28) / 21) + 0x1100).toString()
}
