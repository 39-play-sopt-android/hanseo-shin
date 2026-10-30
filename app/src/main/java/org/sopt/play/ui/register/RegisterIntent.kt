package org.sopt.play.ui.register

sealed interface RegisterIntent {
    data class ChangeName(
        val name: String
    ) : RegisterIntent

    data class ChangeEmail(
        val email: String
    ) : RegisterIntent

    data class ChangePassword(
        val password: String
    ) : RegisterIntent

    data class ChangePasswordConfirm(
        val passwordConfirm: String
    ) : RegisterIntent

    data object ClickRegister : RegisterIntent
}