package kr.mashup.wequiz.application.ranking

import kr.mashup.wequiz.application.answer.QuizAnswerRankingDto
import kr.mashup.wequiz.application.answer.toRankingDto
import kr.mashup.wequiz.repository.answer.MyQuizRankingDto
import kr.mashup.wequiz.repository.answer.QuizAnswerQueryRepository
import kr.mashup.wequiz.repository.answer.QuizAnswerRepository
import kr.mashup.wequiz.repository.answer.SortOrder
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuizRankingService(
    private val quizAnswerRepository: QuizAnswerRepository,
    private val quizAnswerQueryRepository: QuizAnswerQueryRepository
) {

    @Transactional(readOnly = true)
    fun findQuizRanking(quizAnswerId: Long?, quizId: Long, size: Int): List<QuizAnswerRankingDto> {
        val cursorScore = if (quizAnswerId != null) {
            quizAnswerRepository.findByIdOrNull(quizAnswerId)?.totalScore
                ?: throw throw WeQuizException("$quizId 에 해당하는 정답지가 없어요")
        } else {
            Int.MAX_VALUE
        }

        return quizAnswerQueryRepository.findQuizAnswersByQuizId(
            quizAnswerId = quizAnswerId,
            cursorScore = cursorScore,
            quizId = quizId,
            size = size,
            sortOrder = SortOrder.TOTAL_SCORE_DESC
        ).map { it.toRankingDto() }
    }

    @Transactional(readOnly = true)
    fun findMyQuizRanking(cursorUserId: Long?, cursorScore: Int?, quizCreatorId: Long, size: Int): List<MyQuizRankingDto> {

        return quizAnswerQueryRepository.findQuizAnswersByQuizCreatorId(
            cursorUserId = cursorUserId,
            cursorScore = cursorScore ?: Int.MAX_VALUE,
            quizCreatorId = quizCreatorId,
            size = size,
            sortOrder = SortOrder.TOTAL_SCORE_DESC
        )
    }
}
