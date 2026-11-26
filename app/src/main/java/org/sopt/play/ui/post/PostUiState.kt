package org.sopt.play.ui.post

import org.sopt.play.data.model.Post

data class PostUiState(
    val keyword: String = "",
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val showEmpty: Boolean = false,
    val isCreateSheetVisible: Boolean = false,
    val title: String = "",
    val content: String = "",
    val isSubmitting: Boolean = false,
    val deletePostId: Long? = null
) {
    val isSubmitEnabled: Boolean
        get() = title.isNotBlank() &&
                content.isNotBlank() &&
                !isLoading &&
                !isSubmitting
}