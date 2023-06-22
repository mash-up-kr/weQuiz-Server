package kr.mashup.wequiz.domain.statistic

import kr.mashup.wequiz.domain.answer.QuestionAnswer
import kr.mashup.wequiz.domain.quiz.question.Question

class QuestionStatistic(
    private val question: Question,
    private val questionAnswer: QuestionAnswer,
) {
}