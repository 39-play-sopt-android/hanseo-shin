package org.sopt.play.ui.home.model

import androidx.annotation.DrawableRes

data class HomeProduct(
    val id: Int,
    @DrawableRes val imageRes: Int,
    val storeName: String,
    val productName: String,
    val discountRate: Int,
    val price: String,
    val isFavorite: Boolean = false
)
