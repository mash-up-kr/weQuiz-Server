package kr.mashup.wequiz.domain.statistic

import kr.mashup.wequiz.domain.answer.QuizAnswer
import kr.mashup.wequiz.domain.quiz.Quiz

class QuizStatistic(
    private val quiz: Quiz,
    private val answers: List<QuizAnswer>
) {
}