package kr.mashup.wequiz.controller

import kr.mashup.wequiz.domain.exception.WeQuizError
import kr.mashup.wequiz.domain.exception.WeQuizException
import kr.mashup.wequiz.lib.PrefixLogger
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

val logger = PrefixLogger("[ControllerAdvice]") {}

@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(
        exception: RuntimeException
    ): ResponseEntity<WeQuizExceptionDto> {
        logger.warn(exception) { "문제가 발생 했어요." }
        return ResponseEntity.internalServerError()
            .body(WeQuizExceptionDto.default())
    }

    @ExceptionHandler(WeQuizException::class)
    fun handleWeQuizException(
        exception: WeQuizException
    ): ResponseEntity<WeQuizExceptionDto> {
        logger.warn(exception) { "code: ${exception.error.code}, message: ${exception.message}" }
        return ResponseEntity.internalServerError()
            .body(WeQuizExceptionDto.from(exception))
    }

    @ExceptionHandler(HttpMessageConversionException::class)
    fun handleHttpMessageConversionException(
        exception: HttpMessageConversionException
    ): ResponseEntity<WeQuizExceptionDto> {
        return ResponseEntity.badRequest()
            .body(WeQuizExceptionDto.default())
    }
}

data class WeQuizExceptionDto(
    val code: String,
    val message: String,
) {
    companion object {
        fun default(): WeQuizExceptionDto {
            return WeQuizExceptionDto(
                code = WeQuizError.WEC999.code,
                message = "문제가 발생 했어요",
            )
        }

        fun from(exception: WeQuizException): WeQuizExceptionDto {
            return WeQuizExceptionDto(
                code = exception.error.code,
                message = exception.message,
            )
        }
    }
}