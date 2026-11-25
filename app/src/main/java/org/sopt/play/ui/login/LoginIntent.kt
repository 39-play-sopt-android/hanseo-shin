package org.sopt.play.ui.login

sealed interface LoginIntent {
    data class ChangeEmail(
        val email: String
    ) : LoginIntent

    data class ChangePassword(
        val password: String
    ) : LoginIntent

    data object ClickLogin : LoginIntent

    data object ClickRegister : LoginIntent
}