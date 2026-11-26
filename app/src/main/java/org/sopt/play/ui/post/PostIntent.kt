package org.sopt.play.ui.post

sealed interface PostIntent {

    data class ChangeKeyword(
        val keyword: String
    ) : PostIntent

    data object LoadPosts : PostIntent

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
}