package kr.mashup.wequiz.application.statistic

import kr.mashup.wequiz.repository.answer.QuizAnswerRepository
import kr.mashup.wequiz.repository.quiz.QuizRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StatisticService(
    private val quizRepository: QuizRepository,
    private val quizAnswerRepository: QuizAnswerRepository,
) {
    fun getStatistic(quizId: Long) {
        val quiz = quizRepository.findByIdOrNull(quizId) ?: throw IllegalArgumentException()
        val questions = quiz.questions
        val answers = quizAnswerRepository.findAllByQuizId(quiz.id)
    }
}