package org.sopt.play.data.repository

import org.sopt.play.data.model.Post
import org.sopt.play.data.remote.ApiResult

interface PostRepository {

    suspend fun getPosts(): ApiResult<List<Post>>
}