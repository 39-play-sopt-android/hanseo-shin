package org.sopt.play.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayTypography
import org.sopt.play.ui.theme.Red

@Composable
fun ProductCard(
    @DrawableRes imageRes: Int,
    storeName: String,
    productName: String,
    discountRate: Int,
    price: String,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(160f / 200f)
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = productName,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(
                        if (isFavorite) {
                            R.drawable.ic_favorite_fill
                        } else {
                            R.drawable.ic_favorite
                        }
                    ),
                    contentDescription = if (isFavorite) {
                        "좋아요 선택됨"
                    } else {
                        "좋아요 선택 안 됨"
                    },
                    modifier = Modifier.size(24.dp),
                    tint = if (isFavorite) Red else Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    top = 12.dp,
                    end = 8.dp,
                    bottom = 20.dp
                ),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = storeName,
                style = PlayTypography.m14,
                color = Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = productName,
                style = PlayTypography.r14,
                color = Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "$discountRate%",
                    style = PlayTypography.m14,
                    color = Red
                )

                Text(
                    text = price,
                    style = PlayTypography.m14,
                    color = Black
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 328)
@Composable
private fun ProductCardPreview() {
    PlaySoptTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProductCard(
                imageRes = android.R.drawable.ic_menu_gallery,
                storeName = "스토어명",
                productName = "상품명",
                discountRate = 49,
                price = "29,370",
                modifier = Modifier.weight(1f)
            )

            ProductCard(
                imageRes = android.R.drawable.ic_menu_gallery,
                storeName = "한 줄을 넘어가는 아주 긴 스토어명",
                productName = "한 줄을 넘어가는 아주 긴 상품명",
                discountRate = 49,
                price = "29,370",
                modifier = Modifier.weight(1f),
                isFavorite = true
            )
        }
    }
}