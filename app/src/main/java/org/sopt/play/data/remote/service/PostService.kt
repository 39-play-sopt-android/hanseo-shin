package org.sopt.play.data.remote.service

import org.sopt.play.data.remote.dto.BaseResponse
import org.sopt.play.data.remote.dto.CreatePostRequest
import org.sopt.play.data.remote.dto.PostDetailResponse
import org.sopt.play.data.remote.dto.PostListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PostService {

    @GET("api/v1/posts")
    suspend fun getPosts(): BaseResponse<PostListResponse>

    @POST("api/v1/posts")
    suspend fun createPost(
        @Header("Authorization") authorizationCode: String,
        @Body request: CreatePostRequest
    ): BaseResponse<PostDetailResponse>
}