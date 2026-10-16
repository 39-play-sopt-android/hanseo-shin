package org.sopt.play.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.PlayChip
import org.sopt.play.ui.theme.PlaySoptTheme

enum class HomeCategory(val title: String){
    ALL("전체"),
    TOP("상의"),
    PANTS("팬츠"),
    ONE_PIECE("원피스"),
    OUTER("아우터"),
    SKIRT("스커트"),
    SHOES("슈즈"),
    BAG("가방"),
    INNERWEAR("이너웨어/잠옷"),
    PHONE_ACCESSORY("폰꾸미기")
}

@Composable
fun HomeCategoryRow(
    selectedCategory: HomeCategory,
    onCategoryClick: (HomeCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(HomeCategory.entries) { category ->
            PlayChip(
                text = category.title,
                onClick = { onCategoryClick(category) },
                selected = category == selectedCategory
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun HomeCategoryRowPreview() {
    PlaySoptTheme {
        HomeCategoryRow(
            selectedCategory = HomeCategory.ALL,
            onCategoryClick = {}
        )
    }
}