// ktlint-disable filename
package kr.mashup.wequiz.controller.statistic.model

import kr.mashup.wequiz.domain.statistic.QuizStatistic

data class GetQuizStatisticResponse(
    val quizId: Long,
    val quizTitle: String,
    val questions: List<QuestionStatisticsPresentation>
) {
    companion object {
        fun from(quizStatistic: QuizStatistic): GetQuizStatisticResponse {
            return GetQuizStatisticResponse(
                quizId = quizStatistic.quizView.id,
                quizTitle = quizStatistic.quizView.title,
                questions = quizStatistic.quizView.questions.map { question ->
                    QuestionStatisticsPresentation(
                        questionId = question.id,
                        questionTitle = question.title,
                        options = question.options.map { option ->
                            OptionStatisticsPresentation(
                                optionId = option.id,
                                content = option.content,
                                isCorrect = option.isCorrect,
                                selectivity = quizStatistic.findOptionStatistic(question.id, option.id)?.selectivity ?: 0f
                            )
                        }
                    )
                }
            )
        }
    }

    data class QuestionStatisticsPresentation(
        val questionId: Long,
        val questionTitle: String,
        val options: List<OptionStatisticsPresentation>
    )

    data class OptionStatisticsPresentation(
        val optionId: Long,
        val content: String,
        val isCorrect: Boolean,
        val selectivity: Float
    )
}
