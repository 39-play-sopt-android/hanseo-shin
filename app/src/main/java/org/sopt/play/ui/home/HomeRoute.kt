package org.sopt.play.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onMenuClick = { menu ->
            viewModel.processIntent(
                HomeIntent.SelectMenu(menu)
            )
        },
        onCategoryClick = { category ->
            viewModel.processIntent(
                HomeIntent.SelectCategory(category)
            )
        },
        onFavoriteClick = { productId ->
            viewModel.processIntent(
                HomeIntent.ToggleFavorite(productId)
            )
        },
        modifier = modifier
    )
}