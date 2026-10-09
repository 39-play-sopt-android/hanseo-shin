package org.sopt.play.ui.login


import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.component.PlayTextFieldType
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.Gray6
import org.sopt.play.ui.theme.PlayTypography
import org.sopt.play.ui.theme.White
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember

@Composable
fun LoginScreen(
    onLoginClick: (email: String, password: String) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.length >= 6

    val emailError = if (email.isNotEmpty() && !isEmailValid) {
        "올바른 이메일을 입력해주세요."
    } else {
        null
    }

    val passwordError = if (password.isNotEmpty() && !isPasswordValid) {
        "비밀번호는 6자 이상 입력해주세요."
    } else {
        null
    }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val doneKeyboardActions = KeyboardActions(
        onDone = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(top = 60.dp)
    ) {
        Text(
            text = "이메일로 로그인하기",
            style = PlayTypography.b28,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        PlayTextField(
            value = email,
            onValueChange = { email = it },
            label = "이메일 주소",
            placeholder = "abc@email.com",
            type = PlayTextFieldType.Email,
            errorMessage = emailError,
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(32.dp))

        PlayTextField(
            value = password,
            onValueChange = { password = it },
            label = "비밀번호",
            placeholder = "6자 이상의 비밀번호",
            type = PlayTextFieldType.Password,
            errorMessage = passwordError,
            imeAction = ImeAction.Done,
            keyboardActions = doneKeyboardActions
        )

        Spacer(modifier = Modifier.height(40.dp))

        PlayButton(
            text = "로그인",
            enabled = isEmailValid && isPasswordValid,
            onClick = {
                onLoginClick(email, password)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "아직 계정이 없으신가요?",
                style = PlayTypography.m14,
                color = Gray3
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "회원가입하기",
                style = PlayTypography.m14,
                color = Gray6,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onRegisterClick
                    )
                    .padding(horizontal = 12.dp)
            )
        }
    }
}