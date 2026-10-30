package org.sopt.play.ui.login

import android.util.Patterns

data class LoginUiState(
    val email: String = "",
    val password: String = ""
) {
    val isEmailValid: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPasswordValid: Boolean
        get() = password.length >= 6

    val emailError: String?
        get() = if (email.isNotEmpty() && !isEmailValid) {
            "올바른 이메일을 입력해주세요."
        } else {
            null
        }

    val passwordError: String?
        get() = if (password.isNotEmpty() && !isPasswordValid) {
            "비밀번호는 6자 이상 입력해주세요."
        } else {
            null
        }

    val isLoginEnabled: Boolean
        get () = isEmailValid && isPasswordValid
}
