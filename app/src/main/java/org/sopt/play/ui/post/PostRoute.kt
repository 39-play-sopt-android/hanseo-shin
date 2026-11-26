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
import org.sopt.play.ui.post.component.PostBottomSheet
import org.sopt.play.ui.post.component.PostDeleteDialog

@Composable
fun PostRoute(
    authorizationCode: String,
    author: String,
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
        onSearch = {
            viewModel.processIntent(
                PostIntent.SearchPosts
            )
        },
        onCreateClick = {
            viewModel.processIntent(
                PostIntent.OpenCreateSheet
            )
        },
        onPostClick = { postId ->
            viewModel.processIntent(
                PostIntent.OpenEditSheet(
                    postId = postId
                )
            )
        },
        onPostLongClick = { postId ->
            viewModel.processIntent(
                PostIntent.OpenDeleteDialog(
                    postId = postId
                )
            )
        },
        modifier = modifier
    )

    if (uiState.isCreateSheetVisible) {
        PostBottomSheet(
            title = uiState.title,
            content = uiState.content,
            onTitleChange = { title ->
                viewModel.processIntent(
                    PostIntent.ChangeTitle(title)
                )
            },
            onContentChange = { content ->
                viewModel.processIntent(
                    PostIntent.ChangeContent(content)
                )
            },
            onSubmit = {
                viewModel.processIntent(
                    PostIntent.CreatePost(
                        authorizationCode = authorizationCode,
                        author = author
                    )
                )
            },
            onDismissRequest = {
                viewModel.processIntent(
                    PostIntent.DismissCreateSheet
                )
            },
            isSubmitEnabled = uiState.isSubmitEnabled
        )
    }

    if (uiState.deletePostId != null) {
        PostDeleteDialog(
            onDeleteClick = {
                viewModel.processIntent(
                    PostIntent.DeletePost(
                        authorizationCode = authorizationCode
                    )
                )
            },
            onDismissRequest = {
                viewModel.processIntent(
                    PostIntent.DismissDeleteDialog
                )
            }
        )
    }

    if (uiState.isEditSheetVisible) {
        PostBottomSheet(
            title = uiState.title,
            content = uiState.content,
            onTitleChange = { title ->
                viewModel.processIntent(
                    PostIntent.ChangeTitle(title)
                )
            },
            onContentChange = { content ->
                viewModel.processIntent(
                    PostIntent.ChangeContent(content)
                )
            },
            onSubmit = {
                viewModel.processIntent(
                    PostIntent.UpdatePost(
                        authorizationCode = authorizationCode
                    )
                )
            },
            onDismissRequest = {
                viewModel.processIntent(
                    PostIntent.DismissEditSheet
                )
            },
            isSubmitEnabled = uiState.isSubmitEnabled
        )
    }
}