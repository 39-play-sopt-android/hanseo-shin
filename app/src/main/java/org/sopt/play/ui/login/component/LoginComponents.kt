package org.sopt.play.ui.login.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.component.PlayTextFieldType
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.Gray6
import org.sopt.play.ui.theme.PlayTypography
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember

@Composable
fun LoginTitle(modifier: Modifier = Modifier) {
    Text(
        text = "이메일로 로그인하기",
        modifier = modifier,
        style = PlayTypography.b28,
        color = Color.Black
    )
}

@Composable
fun LoginEmailField(
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
fun LoginPasswordField(
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
        label = "비밀번호",
        placeholder = "6자 이상의 비밀번호",
        type = PlayTextFieldType.Password,
        modifier = modifier,
        errorMessage = errorMessage,
        imeAction = ImeAction.Done,
        keyboardActions = doneKeyboardActions
    )
}

@Composable
fun LoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    PlayButton(
        text = "로그인",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun LoginRegisterGuide(
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
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
