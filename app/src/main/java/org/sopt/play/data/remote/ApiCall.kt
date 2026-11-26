package org.sopt.play.data.remote

import java.io.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import org.sopt.play.data.remote.dto.BaseResponse
import retrofit2.HttpException

suspend fun <T : Any> apiCall(
    block: suspend () -> BaseResponse<T>
): ApiResult<T> {
    return try {
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