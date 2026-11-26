package org.sopt.play.ui.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.Gray1
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.Gray5
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayTypography

@Composable
fun PostCard(
    title: String,
    content: String,
    author: String,
    createdAtText: String,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Gray1)
            .combinedClickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = author,
                modifier = Modifier.weight(
                    weight = 1f,
                    fill = false
                ),
                color = Gray3,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = PlayTypography.m12
            )

            Spacer(
                modifier = Modifier
                    .size(width = 1.dp, height = 12.dp)
                    .background(Gray3)
            )

            Text(
                text = createdAtText,
                color = Gray3,
                maxLines = 1,
                style = PlayTypography.m12
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                color = Gray5,
                style = PlayTypography.m16
            )

            Text(
                text = content,
                color = Gray5,
                style = PlayTypography.m14
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun PostCardPreview() {
    PlaySoptTheme {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            PostCard(
                title = "제목",
                content = "본문입니다.",
                author = "작성자",
                createdAtText = "2026.11.26",
                onClick = {},
                onLongClick = {}
            )
        }
    }
}