package org.sopt.play.data.repository

import org.sopt.play.data.model.Post
import org.sopt.play.data.remote.ApiResult

interface PostRepository {

    suspend fun getPosts(
        keyword: String? = null
    ): ApiResult<List<Post>>

    suspend fun createPost(
        authorizationCode: String,
        title: String,
        content: String,
        author: String
    ) : ApiResult<Unit>

    suspend fun deletePost(
        authorizationCode: String,
        postId: Long,
    ) : ApiResult<Unit>

    suspend fun updatePost(
        authorizationCode: String,
        postId: Long,
        title: String,
        content: String
    ): ApiResult<Unit>
}