package org.sopt.play.ui.login

sealed interface LoginSideEffect {
    data object NavigateToHome: LoginSideEffect

    data object NavigateToRegister : LoginSideEffect

    data class ShowToast(
        val message: String
    ) : LoginSideEffect
}