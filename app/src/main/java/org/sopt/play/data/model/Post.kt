package org.sopt.play.data.model

data class Post(
    val id: Long,
    val title: String,
    val content: String,
    val author: String,
    val createdAtText: String
)
