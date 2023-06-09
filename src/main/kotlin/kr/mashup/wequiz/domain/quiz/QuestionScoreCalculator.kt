package kr.mashup.wequiz.domain.quiz

import kr.mashup.wequiz.controller.quiz.model.QuestionDto

interface QuestionScoreCalculator {
    fun calculateScores(questions: List<QuestionDto>): List<Int>
}

/*
 * 단순히 총 점수 / 문제 개수 형태로 점수를 계산해요.
 */
class SimpleQuestionScoreCalculator: QuestionScoreCalculator {
    override fun calculateScores(questions: List<QuestionDto>): List<Int> {
        val totalScore = 100
        val questionCount = questions.size
        val score = totalScore / questionCount
        val remainder = totalScore % questionCount

        return List(questions.size) { index ->
            if (index == questionCount - 1) {
                score + remainder
            } else {
                score
            }
        }
    }
}