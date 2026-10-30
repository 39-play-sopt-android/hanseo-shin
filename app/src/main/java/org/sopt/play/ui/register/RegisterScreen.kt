package org.sopt.play.ui.register

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
import org.sopt.play.ui.register.component.RegisterTitle
import org.sopt.play.ui.register.component.RegisterNameField
import org.sopt.play.ui.register.component.RegisterEmailField
import org.sopt.play.ui.register.component.RegisterPasswordField
import org.sopt.play.ui.register.component.RegisterPasswordConfirmField
import org.sopt.play.ui.register.component.RegisterButton
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.White

@Composable
fun RegisterScreen(
    uiState: RegisterUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    onRegisterClick: (email: String, password: String) -> Unit,
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
        RegisterTitle()

        Spacer(modifier = Modifier.height(40.dp))

        RegisterNameField(
            value = uiState.name,
            onValueChange = onNameChange,
        )

        Spacer(modifier = Modifier.height(32.dp))

        RegisterEmailField(
            value = uiState.email,
            onValueChange = onEmailChange,
            errorMessage = uiState.emailError
        )

        Spacer(modifier = Modifier.height(32.dp))

        RegisterPasswordField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            errorMessage = uiState.passwordError
        )

        Spacer(modifier = Modifier.height(32.dp))

        RegisterPasswordConfirmField(
            value = uiState.passwordConfirm,
            onValueChange = onPasswordConfirmChange,
            errorMessage = uiState.passwordConfirmError
        )

        Spacer(modifier = Modifier.height(40.dp))

        RegisterButton(
            enabled = uiState.isRegisterEnabled,
            onClick = {
                onRegisterClick(uiState.email, uiState.password)
            }
        )
    }
}

@Preview(
    name = "기본",
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun RegisterScreenPreview() {
    PlaySoptTheme {
        RegisterScreen(
            uiState = RegisterUiState(),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordConfirmChange = {},
            onRegisterClick = { _, _ -> }
        )
    }
}

