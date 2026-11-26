package org.sopt.play.ui.login

sealed interface LoginSideEffect {

    data class NavigateToHome(
        val authorizationCode: String
    ) : LoginSideEffect

    data object NavigateToRegister : LoginSideEffect

    data class ShowToast(
        val message: String
    ) : LoginSideEffect
}