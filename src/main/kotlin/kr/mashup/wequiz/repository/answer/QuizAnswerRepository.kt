package kr.mashup.wequiz.repository.answer

import kr.mashup.wequiz.domain.answer.QuizAnswer
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface QuizAnswerRepository : JpaRepository<QuizAnswer, Long> {
    fun findAllByQuizId(quizId: Long): List<QuizAnswer>

    fun findAllByQuizIdAndTotalScoreLessThanAndIdLessThanAndDeletedAtIsNullOrderByTotalScoreDescCreatedAtDesc(
        quizId: Long,
        totalScore: Long, // 커서
        id: Long, // 커서
        pageable: Pageable
    ): List<QuizAnswer>

    fun findAllByQuizIdAndDeletedAtIsNullOrderByTotalScoreDescCreatedAtDesc(
        quizId: Long,
        pageable: Pageable
    ): List<QuizAnswer>
}
