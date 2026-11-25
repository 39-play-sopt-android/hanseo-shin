package org.sopt.play.ui.login.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.Gray6
import org.sopt.play.ui.theme.PlayTypography

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
