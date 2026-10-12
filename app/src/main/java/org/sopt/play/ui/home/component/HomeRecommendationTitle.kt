package org.sopt.play.ui.home.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlayTypography

@Composable
fun HomeRecommendationTitle(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "${name}님을 위한 추천 아이템",
        modifier = modifier,
        style = PlayTypography.sb16,
        color = Black
    )
}