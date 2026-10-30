package org.sopt.play.ui.register

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

class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<RegisterSideEffect>()
    val sideEffect: SharedFlow<RegisterSideEffect> =
        _sideEffect.asSharedFlow()

    fun processIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.ChangeName -> {
                onNameChange(intent.name)
            }

            is RegisterIntent.ChangeEmail -> {
                onEmailChange(intent.email)
            }

            is RegisterIntent.ChangePassword -> {
                onPasswordChange(intent.password)
            }

            is RegisterIntent.ChangePasswordConfirm -> {
                onPasswordConfirmChange(intent.passwordConfirm)
            }

            RegisterIntent.ClickRegister -> {
                register()
            }
        }
    }

    private fun onNameChange(name: String) {
        _uiState.update { currentState ->
            currentState.copy(name = name)
        }
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

    private fun onPasswordConfirmChange(passwordConfirm: String) {
        _uiState.update { currentState ->
            currentState.copy(passwordConfirm = passwordConfirm)
        }
    }

    private fun register() {
        val currentState = _uiState.value

        if (!currentState.isRegisterEnabled) return

        viewModelScope.launch {
            _sideEffect.emit(
                RegisterSideEffect.RegisterSuccess(
                    email = currentState.email,
                    password = currentState.password
                )
            )
        }
    }
}