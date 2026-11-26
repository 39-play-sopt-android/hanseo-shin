package org.sopt.play.ui.post

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.PlayApplication

@Composable
fun PostRoute(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val application =
        context.applicationContext as PlayApplication

    val viewModel: PostViewModel = viewModel {
        PostViewModel(
            postRepository = application.container.postRepository
        )
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel, context) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is PostSideEffect.ShowToast -> {
                    Toast.makeText(
                        context,
                        effect.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    PostScreen(
        uiState = uiState,
        onKeywordChange = { keyword ->
            viewModel.processIntent(
                PostIntent.ChangeKeyword(keyword)
            )
        },
        onSearch = {},
        onCreateClick = {},
        onPostClick = {},
        onPostLongClick = {},
        modifier = modifier
    )
}