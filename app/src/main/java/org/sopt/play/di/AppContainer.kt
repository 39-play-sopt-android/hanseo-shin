package org.sopt.play.di

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.sopt.play.data.remote.service.PostService
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import org.sopt.play.data.repository.PostRepository
import org.sopt.play.data.repository.PostRepositoryImpl

class AppContainer {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
    }

    private val postService: PostService by lazy {
        retrofit.create(PostService::class.java)
    }

    val postRepository: PostRepository by lazy {
        PostRepositoryImpl(
            postService = postService
        )
    }

    private companion object {
        const val BASE_URL = "https://playsopt.o-r.kr/"
    }
}