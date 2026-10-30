package org.sopt.play.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterRoute(
    onRegisterClick: (email: String, password: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentOnRegisterClick by rememberUpdatedState(onRegisterClick)

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is RegisterSideEffect.RegisterSuccess -> {
                    currentOnRegisterClick(
                        effect.email,
                        effect.password
                    )
                }
            }
        }
    }

    RegisterScreen(
        uiState = uiState,
        onNameChange = { name ->
            viewModel.processIntent(
                RegisterIntent.ChangeName(name)
            )
        },
        onEmailChange = { email ->
            viewModel.processIntent(
                RegisterIntent.ChangeEmail(email)
            )
        },
        onPasswordChange = { password ->
            viewModel.processIntent(
                RegisterIntent.ChangePassword(password)
            )
        },
        onPasswordConfirmChange = { passwordConfirm ->
            viewModel.processIntent(
                RegisterIntent.ChangePasswordConfirm(passwordConfirm)
            )
        },
        onRegisterClick = { _, _ ->
            viewModel.processIntent(RegisterIntent.ClickRegister)
        },
        modifier = modifier
    )
}