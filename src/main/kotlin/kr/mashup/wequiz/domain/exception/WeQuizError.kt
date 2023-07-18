package kr.mashup.wequiz.domain.exception

enum class WeQuizError(val code: String, val description: String = "") {
    WEC100("WEC100"),
    WEC999("WEC999", "문제가 발생 했어요")
}
