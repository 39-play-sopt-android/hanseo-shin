package org.sopt.play.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    // 로그인 화면
    @Serializable
    data object Login : Route

    // 회원가입 화면
    @Serializable
    data object Register : Route

    // 홈화면
    @Serializable
    data object Home : Route
}