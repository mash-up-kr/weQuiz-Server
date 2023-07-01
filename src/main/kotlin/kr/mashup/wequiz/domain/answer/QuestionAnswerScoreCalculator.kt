package kr.mashup.wequiz.domain.answer

interface QuestionAnswerScoreCalculator {
    fun calculateScore(questionAnswers: List<QuestionAnswer>): Int
}

internal class StandardQuestionAnswerScoreCalculator : QuestionAnswerScoreCalculator {
    override fun calculateScore(questionAnswers: List<QuestionAnswer>): Int {
        if (questionAnswers.isEmpty()) return 0
        val question = questionAnswers.first().question
        val answersCount = question.getAnswersCount()

        val correctAnswersCount = questionAnswers.count { it.option.isCorrect }

        return if (answersCount == correctAnswersCount) {
            question.score
        } else {
            0
        }
    }
}
