package org.sopt.play.ui.post

import org.sopt.play.data.model.Post

data class PostUiState(
    val keyword: String = "",
    val posts: List<Post> = emptyList(),
    val showEmpty: Boolean = false
)

