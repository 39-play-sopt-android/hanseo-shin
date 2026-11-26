package org.sopt.play.data.remote

import java.io.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import org.sopt.play.data.remote.dto.BaseResponse
import retrofit2.HttpException

// 성공 시 data가 필요한 API
suspend fun <T : Any> apiCall(
    block: suspend () -> BaseResponse<T>
): ApiResult<T> {
    return handleApiException {
        val response = block()

        if (!response.isSuccess) {
            ApiResult.Failure(
                error = ApiError.Server(
                    code = response.code,
                    message = response.message
                )
            )
        } else {
            val data = response.data

            if (data == null) {
                ApiResult.Failure(
                    error = ApiError.InvalidResponse
                )
            } else {
                ApiResult.Success(data)
            }
        }
    }
}

// 성공 시 data가 없어도 되는 API
suspend fun apiCallWithoutData(
    block: suspend () -> BaseResponse<Unit>
): ApiResult<Unit> {
    return handleApiException {
        val response = block()

        if (response.isSuccess) {
            ApiResult.Success(Unit)
        } else {
            ApiResult.Failure(
                error = ApiError.Server(
                    code = response.code,
                    message = response.message
                )
            )
        }
    }
}

// 두 함수에서 사용하는 공통 예외 처리
private suspend fun <T> handleApiException(
    block: suspend () -> ApiResult<T>
): ApiResult<T> {
    return try {
        block()
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        currentCoroutineContext().ensureActive()

        val error = when (exception) {
            is HttpException -> {
                ApiError.Http(
                    statusCode = exception.code()
                )
            }

            is SerializationException -> {
                ApiError.InvalidResponse
            }

            is IOException -> {
                ApiError.Network
            }

            else -> {
                ApiError.Unknown
            }
        }

        ApiResult.Failure(error)
    }
}