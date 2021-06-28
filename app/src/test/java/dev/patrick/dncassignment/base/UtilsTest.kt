package dev.patrick.dncassignment.base

import dev.patrick.dncassignment.domain.model.User
import org.junit.Assert.*
import org.junit.Test

class UtilsTest {

    val fakes = listOf<User>(
        User(1,"David", "David", "", false),
        User(2,"Dong", "Dong", "", false),
        User(3,"Edward", "Edward", "", false),
        User(4,"Kevin", "Kevin", "", false),
        User(5,"", "You", "", false),
        User(6,"", "Your", "", false),
        User(7,"", "Your", "", false),
        User(8,"", "공유", "", false),
        User(9,"", "김종국", "", false),
        User(10,"", "유재석", "", false),
    )

    @Test
    fun givenUserData_whenConvertUiModel_thenReturnCorrectResult() {
        val uiModels = convertUiModel(fakes)
        uiModels.forEach {
            println(it)
        }
        assertEquals(uiModels.size, 16)
    }
}