package org.sopt.play.ui.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.component.PlayTextFieldType
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostBottomSheet(
    title: String,
    content: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onDismissRequest: () -> Unit,
    isSubmitEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        containerColor = White,
        dragHandle = {
            PostSheetDragHandle()
        }
    ) {
        PostBottomSheetContent(
            title = title,
            content = content,
            onTitleChange = onTitleChange,
            onContentChange = onContentChange,
            onSubmit = onSubmit,
            isSubmitEnabled = isSubmitEnabled
        )
    }
}

@Composable
private fun PostSheetDragHandle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 36.dp, height = 4.dp)
                .background(
                    color = Gray3,
                    shape = CircleShape
                )
        )
    }
}

@Composable
private fun PostBottomSheetContent(
    title: String,
    content: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onSubmit: () -> Unit,
    isSubmitEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PlayTextField(
                value = title,
                onValueChange = { newTitle ->
                    if (newTitle.length <= 20) {
                        onTitleChange(newTitle)
                    }
                },
                label = "제목",
                placeholder = "제목을 입력하세요.",
                type = PlayTextFieldType.Text,
                imeAction = ImeAction.Next
            )

            PlayTextField(
                value = content,
                onValueChange = { newContent ->
                    if (newContent.length <= 1_000) {
                        onContentChange(newContent)
                    }
                },
                label = "본문",
                placeholder = "본문을 입력하세요.",
                type = PlayTextFieldType.Text,
                imeAction = ImeAction.Default,
                singleLine = false
            )
        }

        PlayButton(
            text = "완료",
            onClick = onSubmit,
            enabled = isSubmitEnabled
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFE2E2E2,
    widthDp = 760
)
@Composable
private fun PostBottomSheetPreview() {
    PlaySoptTheme {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                ),
                color = White
            ) {
                Column {
                    PostSheetDragHandle()

                    PostBottomSheetContent(
                        title = "",
                        content = "",
                        onTitleChange = {},
                        onContentChange = {},
                        onSubmit = {},
                        isSubmitEnabled = false
                    )
                }
            }

            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                ),
                color = White
            ) {
                Column {
                    PostSheetDragHandle()

                    PostBottomSheetContent(
                        title = "제목은 최대 20자 글자 수 제한",
                        content = "본문은 최대 1000자 글자 수 제한",
                        onTitleChange = {},
                        onContentChange = {},
                        onSubmit = {},
                        isSubmitEnabled = true
                    )
                }
            }
        }
    }
}