package org.sopt.play.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

    RegisterScreen(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onPasswordConfirmChange = viewModel::onPasswordConfirmChange,
        onRegisterClick = onRegisterClick,
        modifier = modifier
    )
}