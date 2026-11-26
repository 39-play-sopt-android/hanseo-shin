package org.sopt.play.data.remote

sealed interface ApiResult<out T> {

    data class Success<T>(
        val data: T
    ) : ApiResult<T>

    data class Failure(
        val error: ApiError
    ) : ApiResult<Nothing>
}