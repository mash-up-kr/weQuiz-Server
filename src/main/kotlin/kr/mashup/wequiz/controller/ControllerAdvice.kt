package kr.mashup.wequiz.controller

import kr.mashup.wequiz.domain.exception.WeQuizAuthException
import kr.mashup.wequiz.domain.exception.WeQuizException
import kr.mashup.wequiz.lib.PrefixLogger
import org.springframework.http.HttpStatus
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
    ): ResponseEntity<ApiResponse<Any>> {
        logger.warn(exception) { "문제가 발생 했어요." }
        return ResponseEntity.internalServerError()
            .body(ApiResponse.failure(WeQuizException()))
    }

    @ExceptionHandler(WeQuizException::class)
    fun handleWeQuizException(
        exception: WeQuizException
    ): ResponseEntity<ApiResponse<Any>> {
        logger.warn(exception) { "code: ${exception.error.code}, message: ${exception.message}" }
        return ResponseEntity.internalServerError()
            .body(ApiResponse.failure(exception))
    }

    @ExceptionHandler(WeQuizAuthException::class)
    fun handleWeQuizAuthException(
        exception: WeQuizException
    ): ResponseEntity<ApiResponse<Any>> {
        logger.warn(exception) { "code: ${exception.error.code}, message: ${exception.message}" }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.failure(exception))
    }

    @ExceptionHandler(HttpMessageConversionException::class)
    fun handleHttpMessageConversionException(
        exception: HttpMessageConversionException
    ): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.badRequest()
            .body(ApiResponse.failure(WeQuizException()))
    }
}