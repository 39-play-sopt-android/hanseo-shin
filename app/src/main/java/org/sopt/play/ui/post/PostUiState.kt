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
    val deletePostId: Long? = null,
    val editingPost: Post? = null
) {
    val isEditSheetVisible: Boolean
        get() = editingPost != null

    val isSubmitEnabled: Boolean
        get() {
            val initialPost = editingPost

            val isChanged = initialPost == null ||
                    title != initialPost.title ||
                    content != initialPost.content

            return title.isNotBlank() &&
                    content.isNotBlank() &&
                    title.length <= 20 &&
                    content.length <= 1_000 &&
                    isChanged &&
                    !isLoading &&
                    !isSubmitting
        }
}