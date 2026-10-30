package org.sopt.play.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.play.R
import org.sopt.play.ui.home.component.HomeCategory
import org.sopt.play.ui.home.component.HomeMenuType
import org.sopt.play.ui.home.model.HomeProduct

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(products = homeProducts))
    var uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun processIntent(intent: HomeIntent) {
        when(intent) {
            is HomeIntent.SelectMenu -> {
                onMenuClick(intent.menu)
            }

            is HomeIntent.SelectCategory -> {
                onCategoryClick(intent.category)
            }

            is HomeIntent.ToggleFavorite -> {
                onFavoriteClick(intent.productId)
            }
        }
    }

    private fun onMenuClick(menu: HomeMenuType) {
        _uiState.update { currentState ->
            currentState.copy(selectedMenu = menu)
        }
    }

    private fun onCategoryClick(category: HomeCategory) {
        _uiState.update { currentState ->
            currentState.copy(selectedCategory = category)
        }
    }

    private fun onFavoriteClick(productId: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                products = currentState.products.map { product ->
                    if (product.id == productId) {
                        product.copy(isFavorite = !product.isFavorite)
                    } else {
                        product
                    }
                }
            )
        }
    }

}

private val homeProducts = listOf(
    HomeProduct(
        id = 1,
        imageRes = R.drawable.img_home_product_1,
        storeName = "트렌디어패럴",
        productName = "[카프리1위🏆/5부,7부,9부] 팬츠",
        discountRate = 36,
        price = "28,800"
    ),
    HomeProduct(
        id = 2,
        imageRes = R.drawable.img_home_product_2,
        storeName = "슬로우앤드",
        productName = "[🍁가을/3기장선택!] made 팬츠",
        discountRate = 50,
        price = "26,820",
        isFavorite = true
    ),
    HomeProduct(
        id = 3,
        imageRes = R.drawable.img_home_product_3,
        storeName = "여넴",
        productName = "보울7부티셔츠",
        discountRate = 10,
        price = "17,100"
    ),
    HomeProduct(
        id = 4,
        imageRes = R.drawable.img_home_product_4,
        storeName = "슬로우앤드",
        productName = "[오픈할인중] made. 도브 셔츠",
        discountRate = 40,
        price = "35,200"
    )
)