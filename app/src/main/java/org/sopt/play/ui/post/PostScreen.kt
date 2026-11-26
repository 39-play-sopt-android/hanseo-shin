package org.sopt.play.ui.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.PlayFloatingButton
import org.sopt.play.ui.post.component.PostCard
import org.sopt.play.ui.post.component.PostSearchField
import org.sopt.play.data.model.Post
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayTypography
import org.sopt.play.ui.theme.White

@Composable
fun PostScreen(
    uiState: PostUiState,
    onKeywordChange: (String) -> Unit,
    onSearch: () -> Unit,
    onCreateClick: () -> Unit,
    onPostClick: (Long) -> Unit,
    onPostLongClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .safeDrawingPadding()
            .imePadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            PostSearchField(
                value = uiState.keyword,
                onValueChange = onKeywordChange,
                onSearch = {
                    focusManager.clearFocus()
                    onSearch()
                },
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 92.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = uiState.posts,
                    key = { post -> post.id }
                ) { post ->
                    PostCard(
                        title = post.title,
                        content = post.content,
                        author = post.author,
                        createdAtText = post.createdAtText,
                        onClick = {
                            onPostClick(post.id)
                        },
                        onLongClick = {
                            onPostLongClick(post.id)
                        }
                    )
                }
            }
        }

        if (uiState.showEmpty && uiState.posts.isEmpty()) {
            Text(
                text = "검색 결과가 없습니다.",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 16.dp),
                color = Gray3,
                textAlign = TextAlign.Center,
                style = PlayTypography.m18
            )
        }

        PlayFloatingButton(
            onClick = {
                focusManager.clearFocus()
                onCreateClick()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 16.dp,
                    bottom = 20.dp
                )
        )
    }
}

@Preview(
    name = "게시글 목록",
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun PostScreenPreview() {
    PlaySoptTheme {
        PostScreen(
            uiState = PostUiState(
                posts = List(8) { index ->
                    Post(
                        id = index.toLong() + 1,
                        title = "제목 ${index + 1}",
                        content = if (index == 0) {
                            "본문입니다.\n여러 줄의 본문도 표시할 수 있어요."
                        } else {
                            "본문입니다."
                        },
                        author = "작성자",
                        createdAtText = "2026.11.26"
                    )
                }
            ),
            onKeywordChange = {},
            onSearch = {},
            onCreateClick = {},
            onPostClick = {},
            onPostLongClick = {}
        )
    }
}

@Preview(
    name = "검색 결과 없음",
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun PostScreenEmptyPreview() {
    PlaySoptTheme {
        PostScreen(
            uiState = PostUiState(
                keyword = "솝트",
                showEmpty = true
            ),
            onKeywordChange = {},
            onSearch = {},
            onCreateClick = {},
            onPostClick = {},
            onPostLongClick = {}
        )
    }
}
