package org.sopt.play.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayTypography

@Composable
fun PlayMenu(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(4.dp)
                .background(
                    color = if (selected) Black else Color.Transparent,
                    shape = CircleShape
                )
        )

        Text(
            text = text,
            style = PlayTypography.m18,
            color = if (selected) Black else Gray3
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeMenuPreview() {
    PlaySoptTheme {
        Row {
            PlayMenu(
                text = "메뉴",
                selected = true,
                onClick = {}
            )
            PlayMenu(
                text = "메뉴",
                selected = false,
                onClick = {}
            )
        }
    }
}