package org.sopt.play.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import org.sopt.play.ui.home.model.HomeProduct
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayTypography

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    var selectedMenu by rememberSaveable { mutableStateOf(HomeMenuType.HOME) }
    var selectedCategory by rememberSaveable { mutableStateOf(HomeCategory.ALL) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HomeTopBar(
            onSearchClick = {}
        )

        HomeMenuRow(
            selectedMenu = selectedMenu,
            onMenuClick = { selectedMenu = it }
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
            selectedCategory = selectedCategory,
            onCategoryClick = { selectedCategory = it },
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        HomeProductGrid(
            products = homeProducts,
            modifier = Modifier.weight(1f)
        )
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

private val homeBannerImages = listOf(
    R.drawable.img_home_banner_1,
    R.drawable.img_home_banner_2,
    R.drawable.img_home_banner_3
)

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun HomeScreenPreview() {
    PlaySoptTheme {
        HomeScreen()
    }
}