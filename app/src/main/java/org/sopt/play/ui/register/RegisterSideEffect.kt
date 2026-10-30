package org.sopt.play.ui.register

sealed interface RegisterSideEffect {
    data class RegisterSuccess(
        val email: String,
        val password: String
    ) : RegisterSideEffect
}