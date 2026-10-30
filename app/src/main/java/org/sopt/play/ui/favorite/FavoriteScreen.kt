package org.sopt.play.ui.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayTypography

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "찜",
            style = PlayTypography.b28,
            color = Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteScreenPreview() {
    PlaySoptTheme {
        FavoriteScreen()
    }
}