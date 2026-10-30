package org.sopt.play.ui.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginRoute(
    registeredEmail: String?,
    registeredPassword: String?,
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val currentOnNavigateToHome by rememberUpdatedState(onNavigateToHome)
    val currentOnNavigateToRegister by rememberUpdatedState(onNavigateToRegister)

    LaunchedEffect(viewModel, registeredEmail, registeredPassword) {
        viewModel.setRegisteredAccount(
            email = registeredEmail,
            password = registeredPassword
        )
    }

    LaunchedEffect(viewModel, context) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                LoginSideEffect.NavigateToHome -> {
                    currentOnNavigateToHome()
                }

                LoginSideEffect.NavigateToRegister -> {
                    currentOnNavigateToRegister()
                }

                is LoginSideEffect.ShowToast -> {
                    Toast.makeText(
                        context,
                        effect.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onEmailChange = { email ->
            viewModel.processIntent(
                LoginIntent.ChangeEmail(email)
            )
        },
        onPasswordChange = { password ->
            viewModel.processIntent(
                LoginIntent.ChangePassword(password)
            )
        },
        onLoginClick = { _, _ ->
            viewModel.processIntent(LoginIntent.ClickLogin)
        },
        onRegisterClick = {
            viewModel.processIntent(LoginIntent.ClickRegister)
        },
        modifier = modifier
    )
}