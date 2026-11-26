package org.sopt.play.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationCodeRequest(
    val part: String
)