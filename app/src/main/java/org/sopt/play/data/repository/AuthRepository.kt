package org.sopt.play.data.repository

import org.sopt.play.data.remote.ApiResult

interface AuthRepository {

    suspend fun issueAuthorizationCode(): ApiResult<String>
}