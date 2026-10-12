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
    All("전체"),
    Top("상의"),
    Pants("팬츠"),
    OnePiece("원피스"),
    Outer("아우터"),
    Skirt("스커트"),
    Shoes("슈즈"),
    Bag("가방"),
    Innerwear("이너웨어/잠옷"),
    PhoneAccessory("폰꾸미기")
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
            selectedCategory = HomeCategory.All,
            onCategoryClick = {}
        )
    }
}