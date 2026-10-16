package org.sopt.play.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.ui.theme.PlaySoptTheme

@Composable
fun HomeBanner(
    images: List<Int>,
    modifier: Modifier = Modifier
) {
    if (images.isEmpty()) return

    // 배너를 한 페이지씩 넘길 수 있도록 HorizontalPager 사용
    val pagerState = rememberPagerState(
        pageCount = { images.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        pageSpacing = 8.dp,
        pageSize = PageSize.Fixed(328.dp)
    ) { page ->
        Image(
            painter = painterResource(images[page]),
            contentDescription = "홈 배너 ${page + 1}",
            modifier = Modifier
                .fillMaxWidth()
                .height(195.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true, widthDp = 380)
@Composable
private fun HomeBannerPreview() {
    PlaySoptTheme {
        HomeBanner(
            images = listOf(
                R.drawable.img_home_banner_1,
                R.drawable.img_home_banner_2,
                R.drawable.img_home_banner_3
            )
        )
    }
}
