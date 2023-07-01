package kr.mashup.wequiz.domain.statistic

import kr.mashup.wequiz.domain.quiz.question.Question

class QuestionStatistic(
    private val question: Question,
    private val optionStatistics: List<OptionStatistic>
) {
    val questionId: Long = question.id

    val options = optionStatistics
}