package org.sopt.play.data.remote.service

import org.sopt.play.data.remote.dto.BaseResponse
import org.sopt.play.data.remote.dto.PostListResponse
import retrofit2.http.GET

interface PostService {

    @GET("api/v1/posts")
    suspend fun getPosts(): BaseResponse<PostListResponse>
}