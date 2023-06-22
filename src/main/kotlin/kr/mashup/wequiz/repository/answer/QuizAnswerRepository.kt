package kr.mashup.wequiz.repository.answer

import kr.mashup.wequiz.domain.answer.QuizAnswer
import org.springframework.data.jpa.repository.JpaRepository

interface QuizAnswerRepository : JpaRepository<QuizAnswer, Long> {
    fun findAllByQuizId(quizId: Long): List<QuizAnswer>
}
