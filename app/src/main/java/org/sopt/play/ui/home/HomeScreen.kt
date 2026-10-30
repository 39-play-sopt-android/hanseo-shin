package org.sopt.play.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.ui.component.HomeTopBar
import org.sopt.play.ui.home.component.HomeBanner
import org.sopt.play.ui.home.component.HomeCategory
import org.sopt.play.ui.home.component.HomeCategoryRow
import org.sopt.play.ui.home.component.HomeMenuRow
import org.sopt.play.ui.home.component.HomeMenuType
import org.sopt.play.ui.home.component.HomeProductGrid
import org.sopt.play.ui.home.component.HomeRecommendationTitle
import org.sopt.play.ui.theme.PlaySoptTheme

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onMenuClick: (HomeMenuType) -> Unit,
    onCategoryClick: (HomeCategory) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HomeTopBar(
            onSearchClick = {}
        )

        HomeProductGrid(
            products = uiState.products,
            onFavoriteClick = onFavoriteClick,
            modifier = Modifier.weight(1f),
            header = {
                Column {
                    HomeMenuRow(
                        selectedMenu = uiState.selectedMenu,
                        onMenuClick = onMenuClick
                    )

                    HomeBanner(
                        images = homeBannerImages
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    HomeRecommendationTitle(
                        name = "김솝트",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    HomeCategoryRow(
                        selectedCategory = uiState.selectedCategory,
                        onCategoryClick = onCategoryClick,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        )
    }
}

private val homeBannerImages = listOf(
    R.drawable.img_home_banner_1,
    R.drawable.img_home_banner_2,
    R.drawable.img_home_banner_3
)

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun HomeScreenPreview() {
    PlaySoptTheme {
        HomeScreen(
            uiState = HomeUiState(),
            onMenuClick = {},
            onCategoryClick = {},
            onFavoriteClick = {},
        )
    }
}