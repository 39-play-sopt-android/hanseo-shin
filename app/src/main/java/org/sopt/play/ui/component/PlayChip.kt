package org.sopt.play.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.Gray2
import org.sopt.play.ui.theme.Gray5
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayTypography
import org.sopt.play.ui.theme.White

@Composable
fun PlayChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    val shape = RoundedCornerShape(6.dp)

    Text(
        text = text,
        modifier = modifier
            .clip(shape)
            .background(if (selected) Gray5 else White)
            .border(
                width = 1.dp,
                color = if (selected) Gray5 else Gray2,
                shape = shape
            )
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        color = if (selected) White else Gray5,
        style = PlayTypography.m14
    )
}

@Preview(showBackground = true)
@Composable
private fun PlayChipPreview() {
    PlaySoptTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PlayChip(
                text = "칩",
                selected = false,
                onClick = {}
            )
            PlayChip(
                text = "칩",
                selected = true,
                onClick = {}
            )
        }
    }
}