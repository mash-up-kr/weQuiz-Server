// ktlint-disable filename
package kr.mashup.wequiz.controller.statistic.model

import kr.mashup.wequiz.domain.statistic.QuizStatistic

data class GetQuizStatisticResponse(
    val quizInfo: QuizInfo
) {
    companion object {
        fun from(quizStatistic: QuizStatistic): GetQuizStatisticResponse {
            return GetQuizStatisticResponse(
                quizInfo = QuizInfo(
                    quizId = quizStatistic.quizView.id,
                    quizTitle = quizStatistic.quizView.title,
                    questions = quizStatistic.quizView.questions.map { question ->
                        QuestionDto(
                            questionId = question.id,
                            questionTitle = question.title,
                            options = question.options.map { option ->
                                OptionDto(
                                    optionId = option.id,
                                    content = option.content,
                                    isCorrect = option.isCorrect,
                                    selectivity = quizStatistic.findOptionStatistic(question.id, option.id)?.selectivity ?: 0f
                                )
                            }
                        )
                    }
                )
            )
        }
    }

    data class QuizInfo(
        val quizId: Long,
        val quizTitle: String,
        val questions: List<QuestionDto>
    )

    data class QuestionDto(
        val questionId: Long,
        val questionTitle: String,
        val options: List<OptionDto>
    )

    data class OptionDto(
        val optionId: Long,
        val content: String,
        val isCorrect: Boolean,
        val selectivity: Float
    )

    data class QuestionStatisticDto(
        val questionId: Long,
        val options: List<OptionStatisticDto>
    )

    data class OptionStatisticDto(
        val optionId: Long,
        val selectivity: Float,
        val isCorrect: Boolean
    )
}
