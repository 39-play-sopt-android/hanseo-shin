package org.sopt.play.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.play.data.remote.ApiError
import org.sopt.play.data.remote.ApiResult
import org.sopt.play.ui.util.toMessage
import org.sopt.play.data.repository.PostRepository

class PostViewModel(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<PostSideEffect>(
        capacity = Channel.BUFFERED
    )
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        loadPosts()
    }

    fun processIntent(intent: PostIntent) {
        when (intent) {
            is PostIntent.ChangeKeyword -> {
                _uiState.update {
                    it.copy(keyword = intent.keyword)
                }
            }

            PostIntent.LoadPosts -> {
                loadPosts()
            }

            PostIntent.OpenCreateSheet -> {
                openCreateSheet()
            }

            PostIntent.DismissCreateSheet -> {
                dismissCreateSheet()
            }

            is PostIntent.ChangeTitle -> {
                changeTitle(intent.title)
            }

            is PostIntent.ChangeContent -> {
                changeContent(intent.content)
            }

            is PostIntent.CreatePost -> {
                createPost(
                    authorizationCode = intent.authorizationCode,
                    author = intent.author
                )
            }
        }
    }

    private fun openCreateSheet() {
        if (_uiState.value.isSubmitting) return

        _uiState.update {
            it.copy(
                isCreateSheetVisible = true,
                title = "",
                content = ""
            )
        }
    }

    private fun dismissCreateSheet() {
        _uiState.update {
            it.copy(
                isCreateSheetVisible = false,
                title = "",
                content = ""
            )
        }
    }

    private fun changeTitle(title: String) {
        if (title.length > 20) return

        _uiState.update {
            it.copy(title = title)
        }
    }

    private fun changeContent(content: String) {
        if (content.length > 1_000) return

        _uiState.update {
            it.copy(content = content)
        }
    }

    private fun loadPosts() {
        val currentState = _uiState.value

        if (currentState.isLoading || currentState.isSubmitting) return

        _uiState.update {
            it.copy(
                isLoading = true,
                showEmpty = false
            )
        }

        viewModelScope.launch {
            try {
                fetchPosts()
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    private suspend fun fetchPosts() {
        when (val result = postRepository.getPosts()) {
            is ApiResult.Success -> {
                _uiState.update {
                    it.copy(posts = result.data)
                }
            }

            is ApiResult.Failure -> {
                showError(
                    error = result.error,
                    fallbackMessage = "게시글 목록을 불러오지 못했어요."
                )
            }
        }
    }

    private fun createPost(
        authorizationCode: String,
        author: String
    ) {
        val currentState = _uiState.value

        if (!currentState.isCreateSheetVisible) return
        if (!currentState.isSubmitEnabled) return

        if (authorizationCode.isBlank() || author.isBlank()) {
            viewModelScope.launch {
                _sideEffect.send(
                    PostSideEffect.ShowToast(
                        message = "로그인 정보를 확인해주세요."
                    )
                )
            }
            return
        }

        _uiState.update {
            it.copy(
                isSubmitting = true,
                isCreateSheetVisible = false,
                title = "",
                content = ""
            )
        }

        viewModelScope.launch {
            try {
                when (
                    val result = postRepository.createPost(
                        authorizationCode = authorizationCode,
                        title = currentState.title,
                        content = currentState.content,
                        author = author
                    )
                ) {
                    is ApiResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true,
                                showEmpty = false
                            )
                        }

                        fetchPosts()
                    }

                    is ApiResult.Failure -> {
                        showError(
                            error = result.error,
                            fallbackMessage = "게시글을 작성하지 못했어요."
                        )
                    }
                }
            } finally {
                _uiState.update {
                    it.copy(
                        isSubmitting = false,
                        isLoading = false
                    )
                }
            }
        }
    }

    private suspend fun showError(
        error: ApiError,
        fallbackMessage: String
    ) {
        _sideEffect.send(
            PostSideEffect.ShowToast(
                message = error.toMessage(fallbackMessage)
            )
        )
    }
}