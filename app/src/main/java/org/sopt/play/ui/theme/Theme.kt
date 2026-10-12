package org.sopt.play.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PlayColorScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,
    secondary = Gray5,
    onSecondary = White,
    tertiary = Gray6,
    onTertiary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    surfaceVariant = Gray1,
    onSurfaceVariant = Gray5,
    outline = Gray2,
    outlineVariant = Gray3,
    error = Red,
    onError = White
)

private val PlayMaterialTypography = Typography(
    headlineLarge = PlayTypography.b28,
    titleMedium = PlayTypography.m18,
    titleSmall = PlayTypography.sb16,
    bodyMedium = PlayTypography.m14,
    labelLarge = PlayTypography.sb14
)

@Composable
fun PlaySoptTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PlayColorScheme,
        typography = PlayMaterialTypography,
        content = content
    )
}