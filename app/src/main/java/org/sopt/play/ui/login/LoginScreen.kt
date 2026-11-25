package org.sopt.play.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.login.component.LoginButton
import org.sopt.play.ui.login.component.LoginEmailField
import org.sopt.play.ui.login.component.LoginPasswordField
import org.sopt.play.ui.login.component.LoginRegisterGuide
import org.sopt.play.ui.login.component.LoginTitle
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.White

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: (email: String, password: String) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(top = 60.dp)
    ) {
        LoginTitle()

        Spacer(modifier = Modifier.height(40.dp))

        LoginEmailField(
            value = uiState.email,
            onValueChange = onEmailChange,
            errorMessage = uiState.emailError
        )

        Spacer(modifier = Modifier.height(32.dp))

        LoginPasswordField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            errorMessage = uiState.passwordError
        )

        Spacer(modifier = Modifier.height(40.dp))

        LoginButton(
            enabled = uiState.isLoginEnabled,
            onClick = { onLoginClick(uiState.email, uiState.password) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        LoginRegisterGuide(onRegisterClick = onRegisterClick)
    }
}

@Preview(
    name = "기본",
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun LoginScreenPreview() {
    PlaySoptTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onLoginClick = { _, _ -> },
            onRegisterClick = {}
        )
    }
}