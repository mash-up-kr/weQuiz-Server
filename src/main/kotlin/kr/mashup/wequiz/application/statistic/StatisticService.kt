package kr.mashup.wequiz.application.statistic

import kr.mashup.wequiz.domain.statistic.QuizStatistic
import kr.mashup.wequiz.repository.answer.QuizAnswerRepository
import kr.mashup.wequiz.repository.quiz.QuizRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StatisticService(
    private val quizRepository: QuizRepository,
    private val quizAnswerRepository: QuizAnswerRepository
) {
    @Transactional(readOnly = true)
    fun getStatistic(quizId: Long): QuizStatistic {
        val quiz = quizRepository.findByIdOrNull(quizId) ?: throw IllegalArgumentException()
        val answers = quizAnswerRepository.findAllByQuizId(quiz.id)
        return QuizStatistic(quiz, answers)
    }
}
