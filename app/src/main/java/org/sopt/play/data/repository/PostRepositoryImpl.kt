package org.sopt.play.data.repository

import org.sopt.play.data.mapper.toPostDisplayDate
import org.sopt.play.data.model.Post
import org.sopt.play.data.remote.ApiResult
import org.sopt.play.data.remote.apiCall
import org.sopt.play.data.remote.apiCallWithoutData
import org.sopt.play.data.remote.dto.CreatePostRequest
import org.sopt.play.data.remote.dto.UpdatePostRequest
import org.sopt.play.data.remote.service.PostService

class PostRepositoryImpl(
    private val postService: PostService
) : PostRepository {

    override suspend fun getPosts(
        keyword: String?
    ): ApiResult<List<Post>> {
        val result = apiCall {
            postService.getPosts(
                keyword = keyword
                    ?.trim()
                    ?.takeIf { it.isNotEmpty() }
            )
        }

        return when (result) {
            is ApiResult.Success -> {
                ApiResult.Success(
                    data = result.data.posts.map { post ->
                        Post(
                            id = post.id,
                            title = post.title,
                            content = post.content,
                            author = post.author,
                            createdAtText = post.createdAt
                                .toPostDisplayDate()
                        )
                    }
                )
            }

            is ApiResult.Failure -> result
        }
    }

    override suspend fun createPost(
        authorizationCode: String,
        title: String,
        content: String,
        author: String
    ): ApiResult<Unit> {
        val result = apiCall {
            postService.createPost(
                authorizationCode = authorizationCode,
                request = CreatePostRequest(
                    title = title,
                    content = content,
                    author = author
                )
            )
        }

        return when (result) {
            is ApiResult.Success -> {
                ApiResult.Success(Unit)
            }

            is ApiResult.Failure -> result
        }
    }

    override suspend fun deletePost(
        authorizationCode: String,
        postId: Long
    ): ApiResult<Unit> {
        return apiCallWithoutData {
            postService.deletePost(
                authorizationCode = authorizationCode,
                postId = postId
            )
        }
    }

    override suspend fun updatePost(
        authorizationCode: String,
        postId: Long,
        title: String,
        content: String
    ): ApiResult<Unit> {
        val result = apiCall {
            postService.updatePost(
                authorizationCode = authorizationCode,
                postId = postId,
                request = UpdatePostRequest(
                    title = title,
                    content = content
                )
            )
        }

        return when (result) {
            is ApiResult.Success -> {
                ApiResult.Success(Unit)
            }

            is ApiResult.Failure -> result
        }
    }
}