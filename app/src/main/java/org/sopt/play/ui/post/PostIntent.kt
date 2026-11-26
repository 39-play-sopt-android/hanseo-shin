package org.sopt.play.ui.post

sealed interface PostIntent {

    data class ChangeKeyword(
        val keyword: String
    ) : PostIntent

    data object LoadPosts : PostIntent
}