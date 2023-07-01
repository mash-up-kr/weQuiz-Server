package kr.mashup.wequiz.application.ranking

import kr.mashup.wequiz.domain.answer.QuizAnswer
import kr.mashup.wequiz.repository.answer.QuizAnswerRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuizRankingService(
    private val quizAnswerRepository: QuizAnswerRepository
) {

    fun getQuizAnswerRanking(quizId: Long, size: Int, cursor: Long? = null): List<QuizAnswer> {
        return if (cursor == null) {
            quizAnswerRepository.findAllByQuizIdAndDeletedAtIsNullOrderByTotalScoreDescCreatedAtDesc(quizId, PageRequest.of(0, size))
        } else {
            val quizAnswer = quizAnswerRepository.findByIdOrNull(cursor) ?: throw RuntimeException("")
            quizAnswerRepository.findAllByQuizIdAndTotalScoreLessThanAndIdLessThanAndDeletedAtIsNullOrderByTotalScoreDescCreatedAtDesc(
                quizId = quizId,
                totalScore = quizAnswer.totalScore.toLong(),
                id = cursor,
                pageable = PageRequest.of(0, size)
            )
        }
    }
}
