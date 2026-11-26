package org.sopt.play.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostDetailResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: String,
    val createdAt: String,
    val updatedAt: String
)