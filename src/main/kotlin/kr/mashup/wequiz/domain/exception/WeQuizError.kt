package kr.mashup.wequiz.domain.exception

enum class WeQuizError(val code: String, val description: String = "") {
    WEC100("WEC100"),

    WEC400("WEC400", "토큰이 없습니다."),
    WEC401("WEC401", "유효하지 않은 토큰 입니다."),

    WEC999("WEC999", "문제가 발생 했어요")
}
