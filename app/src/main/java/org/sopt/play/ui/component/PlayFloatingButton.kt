package org.sopt.play.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.Red
import org.sopt.play.ui.theme.White

@Composable
fun PlayFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .size(60.dp)
            .dropShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 12.dp,
                    spread = 0.dp,
                    color = Color.Black.copy(alpha = 0.15f),
                    offset = DpOffset(
                        x = 0.dp,
                        y = 4.dp
                    )
                )
            ),
        shape = CircleShape,
        containerColor = Red,
        contentColor = White,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp
        )
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_plus),
            contentDescription = "게시글 작성",
            modifier = Modifier.size(28.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlayFloatingButtonPreview() {
    PlaySoptTheme {
        Box(
            modifier = Modifier.padding(24.dp)
        ) {
            PlayFloatingButton(
                onClick = {}
            )
        }
    }
}