package kr.mashup.wequiz.application.statistic

import kr.mashup.wequiz.domain.exception.WeQuizException
import kr.mashup.wequiz.domain.statistic.QuizStatistic
import kr.mashup.wequiz.repository.answer.QuizAnswerRepository
import kr.mashup.wequiz.repository.quiz.QuizRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StatisticService(
    private val quizRepository: QuizRepository,
    private val quizAnswerRepository: QuizAnswerRepository
) {
    @Transactional(readOnly = true)
    fun getStatistic(quizId: Long): QuizStatistic {
        val quiz = quizRepository.findByIdAndDeletedAtIsNull(quizId) ?: throw WeQuizException("퀴즈를 찾을 수 없어요")
        val answers = quizAnswerRepository.findAllByQuizIdAndDeletedAtIsNull(quiz.id)
        return QuizStatistic(quiz, answers)
    }
}
