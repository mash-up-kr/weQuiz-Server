package kr.mashup.wequiz.domain.exception

class WeQuizException(
    val error: WeQuizError,
    override val message: String = error.description,
) : RuntimeException()