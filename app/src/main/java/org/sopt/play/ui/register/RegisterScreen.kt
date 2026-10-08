package org.sopt.play.ui.register

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.component.PlayTextFieldType
import org.sopt.play.ui.theme.PlayTypography
import org.sopt.play.ui.theme.White


@Composable
fun RegisterScreen(
    onRegisterClick: (email: String, password: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordConfirm by rememberSaveable { mutableStateOf("") }

    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.length >= 6
    val isPasswordConfirmValid = passwordConfirm.isNotEmpty() && passwordConfirm == password

    val emailError = if (email.isNotEmpty() && !isEmailValid) {
        "올바른 이메일을 입력해주세요."
    } else {
        null
    }

    val passwordError = if ( password.isNotEmpty() && !isPasswordValid ) {
        "비밀번호는 6자 이상 입력해주세요."
    } else {
        null
    }

    val passwordConfirmError = if ( passwordConfirm.isNotEmpty() && !isPasswordConfirmValid) {
        "비밀번호와 동일하게 입력해주세요."
    } else {
        null
    }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val nextKeyboardActions = KeyboardActions(
        onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }
    )

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
            text = "이메일로 회원가입",
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
            imeAction = ImeAction.Next,
            keyboardActions = nextKeyboardActions
        )

        Spacer(modifier = Modifier.height(32.dp))

        PlayTextField(
            value = password,
            onValueChange = { password = it },
            label = "비밀번호",
            placeholder = "6자 이상의 비밀번호",
            type = PlayTextFieldType.Password,
            errorMessage = passwordError,
            imeAction = ImeAction.Next,
            keyboardActions = nextKeyboardActions
        )

        Spacer(modifier = Modifier.height(32.dp))

        PlayTextField(
            value = passwordConfirm,
            onValueChange = { passwordConfirm = it },
            label = "비밀번호 확인",
            placeholder = "비밀번호를 다시 입력해주세요.",
            type = PlayTextFieldType.Password,
            errorMessage = passwordConfirmError,
            imeAction = ImeAction.Done,
            keyboardActions = doneKeyboardActions
        )

        Spacer(modifier = Modifier.height(40.dp))

        PlayButton(
            text = "회원가입",
            enabled = isEmailValid &&
                    isPasswordValid &&
                    isPasswordConfirmValid,
            onClick = {
                onRegisterClick(email, password)
            }
        )
    }


}
