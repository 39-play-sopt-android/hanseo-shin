package org.sopt.play.ui.post

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun PostRoute(
    modifier: Modifier = Modifier
) {
    var keyword by rememberSaveable {
        mutableStateOf("")
    }

    PostScreen(
        uiState = PostUiState(
            keyword = keyword
        ),
        onKeywordChange = { keyword = it },
        onSearch = {},
        onCreateClick = {},
        onPostClick = {},
        onPostLongClick = {},
        modifier = modifier
    )
}