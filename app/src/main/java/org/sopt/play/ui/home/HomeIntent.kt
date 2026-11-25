package org.sopt.play.ui.home

import org.sopt.play.ui.home.component.HomeCategory
import org.sopt.play.ui.home.component.HomeMenuType

sealed interface HomeIntent {
    data class SelectMenu(
        val menu: HomeMenuType
    ) : HomeIntent

    data class SelectCategory(
        val category: HomeCategory
    ) : HomeIntent

    data class ToggleFavorite(
        val productId: Int
    ) : HomeIntent
}