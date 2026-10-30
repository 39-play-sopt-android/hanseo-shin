package org.sopt.play.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.ui.component.BottomTab
import org.sopt.play.ui.component.PlayBottomBar
import org.sopt.play.ui.favorite.FavoriteScreen
import org.sopt.play.ui.home.HomeRoute
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.White
import org.sopt.play.ui.mypage.MyPageScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    var selectedTab by rememberSaveable {
        mutableStateOf(BottomTab.Home)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = White,
        bottomBar = {
            PlayBottomBar(
                selectedTab = selectedTab,
                onTabClick = { tab ->
                    selectedTab = tab
                },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (selectedTab) {
                BottomTab.Home -> HomeRoute()
                BottomTab.Favorite -> FavoriteScreen()
                BottomTab.MyPage -> MyPageScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    PlaySoptTheme {
        MainScreen()
    }
}