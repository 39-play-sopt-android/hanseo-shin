package org.sopt.play.ui.login

import android.R.attr.password
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<LoginSideEffect>()
    val sideEffect: SharedFlow<LoginSideEffect> = _sideEffect.asSharedFlow()

    private var registeredEmail: String? = null
    private var registeredPassword: String? = null

    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.ChangeEmail -> {
                onEmailChange(intent.email)
            }

            is LoginIntent.ChangePassword -> {
                onPasswordChange(intent.password)
            }

            LoginIntent.ClickLogin -> {
                login()
            }

            LoginIntent.ClickRegister -> {
                sendSideEffect(
                    LoginSideEffect.NavigateToRegister
                )
            }
        }
    }

    fun setRegisteredAccount(
        email: String?,
        password: String?
    ) {
        registeredEmail = email
        registeredPassword = password
    }

    private fun onEmailChange(email: String) {
        _uiState.update { currentState ->
            currentState.copy(email = email)
        }
    }

    private fun onPasswordChange(password: String) {
        _uiState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    private fun login() {
        val currentState = _uiState.value

        if (!currentState.isLoginEnabled) return

        val isAccountMatched =
            registeredEmail != null &&
                    registeredPassword != null &&
                    currentState.email == registeredEmail &&
                    currentState.password == registeredPassword

        if (isAccountMatched) {
            sendSideEffect(
                LoginSideEffect.NavigateToHome
            )
        } else {
            sendSideEffect(
                LoginSideEffect.ShowToast(
                    message = "이메일 또는 비밀번호가 올바르지 않아요."
                )
            )
        }
    }

    private fun sendSideEffect(effect: LoginSideEffect) {
        viewModelScope.launch {
            _sideEffect.emit(effect)
        }
    }
}
