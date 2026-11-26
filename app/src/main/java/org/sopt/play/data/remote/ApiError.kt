package org.sopt.play.data.remote

sealed interface ApiError {

    // HTTP 응답을 받지 못한 연결 오류
    data object Network : ApiError

    // HTTP 상태 코드가 성공 범위가 아닌 경우
    data class Http(
        val statusCode: Int
    ) : ApiError

    // HTTP는 성공했지만 서버의 isSuccess가 false인 경우
    data class Server(
        val code: String,
        val message: String
    ) : ApiError

    // JSON 구조가 다르거나 필수 응답 데이터가 누락된 경우
    data object InvalidResponse : ApiError

    // 그 밖의 예상하지 못한 오류
    data object Unknown : ApiError
}