package org.sopt.play.ui.home.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.ui.component.PlayMenu
import org.sopt.play.ui.theme.PlaySoptTheme

enum class HomeMenuType(val title: String) {
    DOUBLE_REWARD("2배적립"),
    HOME("홈"),
    SELECTED("셀렉티드"),
    BRAND("브랜드"),
    BEAUTY("뷰티"),
    LIFE("라이프"),
    RANKING("랭킹")
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
            selectedMenu = HomeMenuType.HOME,
            onMenuClick = {}
        )
    }
}