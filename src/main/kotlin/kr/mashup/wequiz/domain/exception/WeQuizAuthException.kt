package kr.mashup.wequiz.domain.exception

class WeQuizAuthException(
    error: WeQuizError
) : WeQuizException(
    error = error
)
