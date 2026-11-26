package org.sopt.play.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.play.PlayApplication
import org.sopt.play.data.remote.ApiError
import org.sopt.play.data.remote.ApiResult
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
                changeKeyword(intent.keyword)
            }

            PostIntent.LoadPosts -> {
                loadPosts()
            }
        }
    }

    private fun changeKeyword(keyword: String) {
        _uiState.update { currentState ->
            currentState.copy(
                keyword = keyword
            )
        }
    }

    private fun loadPosts() {
        if (_uiState.value.isLoading) return

        _uiState.update { currentState ->
            currentState.copy(
                isLoading = true,
                showEmpty = false
            )
        }

        viewModelScope.launch {
            try {
                when (val result = postRepository.getPosts()) {
                    is ApiResult.Success -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                posts = result.data
                            )
                        }
                    }

                    is ApiResult.Failure -> {
                        _sideEffect.send(
                            PostSideEffect.ShowToast(
                                message = result.error.toMessage()
                            )
                        )
                    }
                }
            } finally {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun ApiError.toMessage(): String {
        return when (this) {
            ApiError.Network -> {
                "네트워크 연결을 확인해주세요."
            }

            is ApiError.Http -> {
                "게시글을 불러오지 못했어요. 잠시 후 다시 시도해주세요."
            }

            is ApiError.Server -> {
                message.ifBlank {
                    "게시글을 불러오지 못했어요."
                }
            }

            ApiError.InvalidResponse -> {
                "서버 응답을 확인할 수 없어요."
            }

            ApiError.Unknown -> {
                "알 수 없는 오류가 발생했어요."
            }
        }
    }
}