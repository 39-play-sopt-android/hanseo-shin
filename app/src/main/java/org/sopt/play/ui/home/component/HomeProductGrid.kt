package org.sopt.play.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.ProductCard
import org.sopt.play.ui.home.model.HomeProduct
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.R

@Composable
fun HomeProductGrid(
    products: List<HomeProduct>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 96.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(
            items = products,
            key = { product -> product.id}
        ) { product ->
            ProductCard(
                imageRes = product.imageRes,
                storeName = product.storeName,
                productName = product.productName,
                discountRate = product.discountRate,
                price = product.price,
                isFavorite = product.isFavorite
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 700)
@Composable
private fun HomeProductGridPreview() {
    PlaySoptTheme {
        HomeProductGrid(
            products = listOf(
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
        )
    }
}