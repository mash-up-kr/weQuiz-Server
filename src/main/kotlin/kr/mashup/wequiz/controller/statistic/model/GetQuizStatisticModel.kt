package kr.mashup.wequiz.controller.statistic.model

import kr.mashup.wequiz.domain.statistic.QuizStatistic

data class GetQuizStatisticResponse(
    val quizId: Long
) {
    companion object {
        fun from(quizStatistic: QuizStatistic): GetQuizStatisticResponse {
            return GetQuizStatisticResponse(
                quizId = quizStatistic.quizId
            )
        }
    }
}