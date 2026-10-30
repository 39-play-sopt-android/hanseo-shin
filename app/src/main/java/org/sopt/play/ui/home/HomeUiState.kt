package org.sopt.play.ui.home

import org.sopt.play.ui.home.component.HomeCategory
import org.sopt.play.ui.home.component.HomeMenuType
import org.sopt.play.ui.home.model.HomeProduct

data class HomeUiState (
    val selectedMenu: HomeMenuType = HomeMenuType.HOME,
    val selectedCategory: HomeCategory = HomeCategory.ALL,
    val products: List<HomeProduct> = emptyList()
)