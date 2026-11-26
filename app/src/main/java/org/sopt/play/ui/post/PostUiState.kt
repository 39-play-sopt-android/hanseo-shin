package org.sopt.play.ui.post

import org.sopt.play.data.model.Post

data class PostUiState(
    val keyword: String = "",
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val showEmpty: Boolean = false
)

