package org.sopt.play.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val data: T? = null
)