package org.sopt.play.ui.register

import android.util.Patterns

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirm: String = ""
) {
    val isEmailValid: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPasswordValid: Boolean
        get() = password.length >= 6

    val isPasswordConfirmValid: Boolean
        get() = passwordConfirm.isNotEmpty() &&
                passwordConfirm == password

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

    val passwordConfirmError: String?
        get() = if (
            passwordConfirm.isNotEmpty() &&
            !isPasswordConfirmValid
        ) {
            "비밀번호와 동일하게 입력해주세요."
        } else {
            null
        }

    val isRegisterEnabled: Boolean
        get() = name.isNotBlank() &&
                isEmailValid &&
                isPasswordValid &&
                isPasswordConfirmValid
}
