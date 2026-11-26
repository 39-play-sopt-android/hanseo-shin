package org.sopt.play.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostListResponse(
    val posts: List<PostResponse>,
    val nextCursor: Long? = null,
    val hasNext: Boolean
)

@Serializable
data class PostResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: String,
    val createdAt: String
)