package org.sopt.play.ui.post.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.Gray1
import org.sopt.play.ui.theme.Gray5
import org.sopt.play.ui.theme.Gray6
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayTypography
import org.sopt.play.ui.theme.White

@Composable
fun PostDeleteDialog(
    onDeleteClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        PostDeleteDialogContent(
            onDeleteClick = onDeleteClick,
            onDismissRequest = onDismissRequest,
            modifier = modifier
        )
    }
}

@Composable
private fun PostDeleteDialogContent(
    onDeleteClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .widthIn(max = 288.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = White
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "게시글을 삭제할까요?",
                modifier = Modifier.fillMaxWidth(),
                color = Gray6,
                textAlign = TextAlign.Center,
                style = PlayTypography.m18
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PlayButton(
                    text = "취소",
                    onClick = onDismissRequest,
                    modifier = Modifier.weight(1f),
                    containerColor = Gray1,
                    contentColor = Gray5
                )

                PlayButton(
                    text = "삭제",
                    onClick = onDeleteClick,
                    modifier = Modifier.weight(1f),
                    containerColor = Black,
                    contentColor = Gray1
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFE2E2E2,
    widthDp = 328
)
@Composable
private fun PostDeleteDialogPreview() {
    PlaySoptTheme {
        Box(
            modifier = Modifier.padding(20.dp)
        ) {
            PostDeleteDialogContent(
                onDeleteClick = {},
                onDismissRequest = {}
            )
        }
    }
}