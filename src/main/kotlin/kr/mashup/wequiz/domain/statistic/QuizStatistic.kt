package kr.mashup.wequiz.domain.statistic

import kr.mashup.wequiz.domain.answer.QuizAnswer
import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.quiz.view.QuizView

class QuizStatistic(
    private val quiz: Quiz,
    private val answers: List<QuizAnswer>
) {
    val quizView: QuizView = QuizView.from(quiz)

    val statistic: List<QuestionStatistic> = answers.map { it.questionAnswers }.flatten()
        .groupBy { it.question }
        .mapValues { (_, value) ->
            value.groupBy { it.option }
                .mapValues { (_, value) -> value.size }
        }.map { (key, value) ->
            QuestionStatistic(
                key,
                value.map { option -> OptionStatistic(option.key, option.value, value.values.sumOf { it }) }
            )
        }
}
