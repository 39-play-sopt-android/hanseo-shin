package org.sopt.play.data.repository

import org.sopt.play.data.remote.ApiError
import org.sopt.play.data.remote.ApiResult
import org.sopt.play.data.remote.apiCall
import org.sopt.play.data.remote.dto.AuthorizationCodeRequest
import org.sopt.play.data.remote.service.AuthService

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun issueAuthorizationCode(): ApiResult<String> {
        val result = apiCall {
            authService.issueAuthorizationCode(
                request = AuthorizationCodeRequest(
                    part = "ANDROID"
                )
            )
        }

        return when (result) {
            is ApiResult.Success -> {
                val authorizationCode = result.data.authorizationCode

                if (authorizationCode.isBlank()) {
                    ApiResult.Failure(
                        error = ApiError.InvalidResponse
                    )
                } else {
                    ApiResult.Success(
                        data = authorizationCode
                    )
                }
            }

            is ApiResult.Failure -> result
        }
    }
}