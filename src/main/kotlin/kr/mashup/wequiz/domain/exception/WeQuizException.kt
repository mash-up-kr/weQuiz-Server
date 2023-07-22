package kr.mashup.wequiz.domain.exception

open class WeQuizException(
    val error: WeQuizError = WeQuizError.WEC999,
    override val message: String = error.description
) : RuntimeException() {
    constructor(message: String) : this(WeQuizError.WEC999, message)
}
