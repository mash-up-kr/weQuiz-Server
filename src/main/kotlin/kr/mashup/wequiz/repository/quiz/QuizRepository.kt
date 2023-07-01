package kr.mashup.wequiz.repository.quiz

import kr.mashup.wequiz.domain.quiz.Quiz
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface QuizRepository : JpaRepository<Quiz, Long> {
    fun findAllByUserIdAndIsDeleteIsFalseOrderByIdDesc(
        userId: Long,
        pageable: Pageable
    ): List<Quiz>

    fun findAllByIdAndUserIdBeforeAndIsDeleteIsFalseOrderByIdDesc(
        id: Long,
        userId: Long,
        pageable: Pageable
    ): List<Quiz>
}
