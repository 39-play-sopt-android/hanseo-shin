package org.sopt.play.ui.preview

import androidx.compose.ui.tooling.preview.Preview

@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.BINARY)
@Preview(
    name = "기본",
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Preview(
    name = "큰 글꼴",
    showBackground = true,
    widthDp = 360,
    heightDp = 800,
    fontScale = 1.5f
)
annotation class PlayScreenPreviewAnnotation