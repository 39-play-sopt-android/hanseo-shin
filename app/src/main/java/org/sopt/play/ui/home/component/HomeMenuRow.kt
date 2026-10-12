package org.sopt.play.ui.home.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.ui.component.PlayMenu
import org.sopt.play.ui.theme.PlaySoptTheme

enum class HomeMenuType(val title: String) {
    DoubleReward("2배적립"),
    Home("홈"),
    Selected("셀렉티드"),
    Brand("브랜드"),
    Beauty("뷰티"),
    Life("라이프"),
    Ranking("랭킹")
}

@Composable
fun HomeMenuRow(
    selectedMenu: HomeMenuType,
    onMenuClick: (HomeMenuType) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        items(HomeMenuType.entries) { menu ->
            PlayMenu(
                text = menu.title,
                selected = menu == selectedMenu,
                onClick = { onMenuClick(menu) }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun HomeMenuRowPreview() {
    PlaySoptTheme {
        HomeMenuRow(
            selectedMenu = HomeMenuType.Home,
            onMenuClick = {}
        )
    }
}