package org.sopt.play.ui.login.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.sopt.play.ui.theme.PlayTypography

@Composable
fun LoginTitle(modifier: Modifier = Modifier) {
    Text(
        text = "이메일로 로그인하기",
        modifier = modifier,
        style = PlayTypography.b28,
        color = Color.Black
    )
}
