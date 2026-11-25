package org.sopt.play.ui.login.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sopt.play.ui.component.PlayButton

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
