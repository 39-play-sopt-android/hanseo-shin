package org.sopt.play.ui.register.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.component.PlayTextFieldType
import org.sopt.play.ui.theme.PlayTypography

@Composable
fun RegisterTitle(modifier: Modifier = Modifier) {
    Text(
        text = "이메일로 회원가입",
        modifier = modifier,
        style = PlayTypography.b28,
        color = Color.Black
    )
}

@Composable
fun RegisterNameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    PlayTextField(
        value = value,
        onValueChange = onValueChange,
        label = "이름",
        placeholder = "홍길동",
        type = PlayTextFieldType.Text,
        modifier = modifier,
        imeAction = ImeAction.Next
    )
}

@Composable
fun RegisterEmailField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null
) {
    PlayTextField(
        value = value,
        onValueChange = onValueChange,
        label = "이메일 주소",
        placeholder = "abc@email.com",
        type = PlayTextFieldType.Email,
        modifier = modifier,
        errorMessage = errorMessage,
        imeAction = ImeAction.Next
    )
}

@Composable
fun RegisterPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null
) {
    PlayTextField(
        value = value,
        onValueChange = onValueChange,
        label = "비밀번호",
        placeholder = "6자 이상의 비밀번호",
        type = PlayTextFieldType.Password,
        modifier = modifier,
        errorMessage = errorMessage,
        imeAction = ImeAction.Next
    )
}

@Composable
fun RegisterPasswordConfirmField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val doneKeyboardActions = KeyboardActions(
        onDone = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    )

    PlayTextField(
        value = value,
        onValueChange = onValueChange,
        label = "비밀번호 확인",
        placeholder = "비밀번호를 다시 입력해주세요.",
        type = PlayTextFieldType.Password,
        modifier = modifier,
        errorMessage = errorMessage,
        imeAction = ImeAction.Done,
        keyboardActions = doneKeyboardActions
    )
}

@Composable
fun RegisterButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    PlayButton(
        text = "회원가입",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}
