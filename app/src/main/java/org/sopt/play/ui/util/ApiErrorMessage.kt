package org.sopt.play.ui.util

import org.sopt.play.data.remote.ApiError

fun ApiError.toMessage(
    fallbackMessage: String = "요청을 처리하지 못했어요."
): String {
    return when (this) {
        ApiError.Network -> "네트워크 연결을 확인해주세요."
        is ApiError.Http -> fallbackMessage
        is ApiError.Server -> message.ifBlank { fallbackMessage }
        ApiError.InvalidResponse -> "서버 응답을 확인할 수 없어요."
        ApiError.Unknown -> "알 수 없는 오류가 발생했어요."
    }
}
