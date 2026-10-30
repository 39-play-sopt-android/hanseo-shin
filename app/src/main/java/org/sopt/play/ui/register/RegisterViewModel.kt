package org.sopt.play.ui.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUisState())
    val uiState: StateFlow<RegisterUisState> = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.update { currentState ->
            currentState.copy(name = name)
        }
    }

    fun onEmailChange(email: String) {
        _uiState.update { currentState ->
            currentState.copy(email = email)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    fun onPasswordConfirmChange(passwordConfirm: String) {
        _uiState.update { currentState ->
            currentState.copy(passwordConfirm = passwordConfirm)
        }
    }

}