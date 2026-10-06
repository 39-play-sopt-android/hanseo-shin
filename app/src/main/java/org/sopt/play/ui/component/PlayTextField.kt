package org.sopt.play.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.Gray2
import org.sopt.play.ui.theme.Gray5
import org.sopt.play.ui.theme.Gray6
import org.sopt.play.ui.theme.PlayTypography
import org.sopt.play.ui.theme.Red

enum class PlayTextFieldType {
    Text,
    Email,
    Password
}

@Composable
fun PlayTextFieldType(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    type: PlayTextFieldType,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
) {
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = when {
        errorMessage != null -> Red
        isFocused -> Gray5
        else -> Gray2
    }

    val keyboardType = when(type) {
        PlayTextFieldType.Text -> KeyboardType.Text
        PlayTextFieldType.Email -> KeyboardType.Email
        PlayTextFieldType.Password -> KeyboardType.Password
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        // 상단 라벨
        Text(
            text = label,
            style = PlayTypography.sb16,
            color = Gray6,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged{ isFocused = it.isFocused }
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp, vertical = 16.dp),
            textStyle = PlayTypography.m18,
            singleLine = true,
            cursorBrush = SolidColor(Gray5),
            visualTransformation = if (
                type == PlayTextFieldType.Password
            ) {
                PasswordVisualTransformation(mask = '•')
            } else {
                VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            decorationBox = { innerTextField ->
                Box {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = PlayTypography.m18,
                            color = Gray2
                        )
                    }

                    innerTextField()
                }
            }
        )

        if (errorMessage != null){
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = errorMessage,
                style = PlayTypography.m14,
                color = Red,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}