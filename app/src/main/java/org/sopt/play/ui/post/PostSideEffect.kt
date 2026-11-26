package org.sopt.play.ui.post

sealed interface PostSideEffect {

    data class ShowToast(
        val message: String
    ) : PostSideEffect
}