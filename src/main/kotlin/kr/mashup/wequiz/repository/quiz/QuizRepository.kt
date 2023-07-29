package kr.mashup.wequiz.repository.quiz

import kr.mashup.wequiz.domain.quiz.Quiz
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface QuizRepository : JpaRepository<Quiz, Long> {

    fun findByIdAndDeletedAtIsNull(id: Long): Quiz?

    fun findAllByUserIdAndDeletedAtIsNullOrderByIdDesc(
        userId: Long,
        pageable: Pageable
    ): List<Quiz>

    fun findAllByIdAndUserIdBeforeAndDeletedAtIsNullOrderByIdDesc(
        id: Long,
        userId: Long,
        pageable: Pageable
    ): List<Quiz>
}
