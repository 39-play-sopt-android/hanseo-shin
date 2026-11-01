package org.sopt.play.ui.login.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.component.PlayTextFieldType

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
