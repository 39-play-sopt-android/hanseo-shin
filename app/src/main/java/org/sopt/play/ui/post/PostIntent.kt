package org.sopt.play.ui.post

sealed interface PostIntent {

    data class ChangeKeyword(
        val keyword: String
    ) : PostIntent

    data object LoadPosts : PostIntent

    data object SearchPosts : PostIntent

    data object OpenCreateSheet : PostIntent

    data object DismissCreateSheet : PostIntent

    data class ChangeTitle(
        val title: String
    ) : PostIntent

    data class ChangeContent(
        val content: String
    ) : PostIntent

    data class CreatePost(
        val authorizationCode: String,
        val author: String
    ) : PostIntent

    data class OpenDeleteDialog(
        val postId: Long
    ) : PostIntent

    data object DismissDeleteDialog : PostIntent

    data class DeletePost(
        val authorizationCode: String
    ) : PostIntent

    data class OpenEditSheet(
        val postId: Long
    ) : PostIntent

    data object DismissEditSheet : PostIntent

    data class UpdatePost(
        val authorizationCode: String
    ) : PostIntent
}