package org.sopt.play.ui.login.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.component.PlayTextFieldType

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
