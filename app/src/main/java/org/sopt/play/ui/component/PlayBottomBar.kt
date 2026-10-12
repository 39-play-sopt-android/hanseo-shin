package org.sopt.play.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.Gray5
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayTypography
import org.sopt.play.ui.theme.White

enum class BottomTab(
    val title: String,
    @DrawableRes val iconRes: Int,
    @DrawableRes val selectedIconRes: Int
) {
    Home(
        title = "홈",
        iconRes = R.drawable.ic_home,
        selectedIconRes = R.drawable.ic_home_fill
    ),
    Favorite(
        title = "찜",
        iconRes = R.drawable.ic_favorite,
        selectedIconRes = R.drawable.ic_favorite_fill
    ),
    MyPage(
        title = "마이페이지",
        iconRes = R.drawable.ic_user,
        selectedIconRes = R.drawable.ic_user_fill
    )
}

@Composable
fun PlayBottomBar(
    selectedTab: BottomTab,
    onTabClick: (BottomTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
    ) {
        BottomTab.entries.forEach { tab ->
            val selected = tab == selectedTab
            val color = if (selected) Black else Gray5

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = null,
                        onClick = { onTabClick(tab) }
                    )
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Icon(
                    painter = painterResource(
                        if (selected) {
                            tab.selectedIconRes
                        } else {
                            tab.iconRes
                        }
                    ),
                    contentDescription = if (selected) {
                        "선택한 탭"
                    } else {
                        "선택안한 탭"
                    },
                    modifier = Modifier.size(24.dp),
                    tint = color
                )

                Text(
                    text = tab.title,
                    color = color,
                    style = PlayTypography.sb14
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun PlayBottomBarPreview() {
    PlaySoptTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BottomTab.entries.forEach { tab ->
               PlayBottomBar(
                    selectedTab = tab,
                    onTabClick = {}
                )
            }
        }
    }
}