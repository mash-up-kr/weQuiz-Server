package kr.mashup.wequiz.controller

import com.fasterxml.jackson.annotation.JsonInclude
import kr.mashup.wequiz.domain.exception.WeQuizException

@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiResponse<T> private constructor(
    val code: String,
    val data: T? = null,
    val message: String? = null,
) {
    companion object {
        fun <T> success(result: T?): ApiResponse<T> {
            return ApiResponse(
                code = "SUCCESS",
                data = result,
            )
        }

        fun failure(wequizException: WeQuizException): ApiResponse<Any> {
            return ApiResponse(
                code = wequizException.error.code,
                message = wequizException.message,
            )
        }
    }
}
