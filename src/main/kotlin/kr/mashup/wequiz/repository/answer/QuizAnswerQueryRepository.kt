package kr.mashup.wequiz.repository.answer

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.mashup.wequiz.domain.answer.QQuizAnswer.quizAnswer
import kr.mashup.wequiz.domain.answer.QuizAnswer
import kr.mashup.wequiz.domain.quiz.QQuiz.quiz
import org.springframework.stereotype.Repository

interface QuizAnswerQueryRepository {
    fun findQuizAnswersByQuizId(
        quizAnswerId: Long?,
        cursorScore: Int? = Int.MAX_VALUE,
        quizId: Long,
        sortOrder: SortOrder,
        size: Int = DEFAULT_SIZE
    ): List<QuizAnswer>

    fun findQuizAnswersByQuizCreatorId(
        quizAnswerId: Long?,
        cursorScore: Int = Int.MAX_VALUE,
        quizCreatorId: Long,
        sortOrder: SortOrder,
        size: Int = DEFAULT_SIZE
    ): List<QuizAnswer>

    companion object {
        const val DEFAULT_SIZE = 5
    }
}

enum class SortOrder(val description: String) {
    TOTAL_SCORE_DESC("총 점수 순")
}

@Repository
internal class QueryDslQuizAnswerRepository(
    private val jpaQueryFactory: JPAQueryFactory
) : QuizAnswerQueryRepository {
    override fun findQuizAnswersByQuizId(quizAnswerId: Long?, cursorScore: Int?, quizId: Long, sortOrder: SortOrder, size: Int): List<QuizAnswer> {
        return jpaQueryFactory.selectFrom(quizAnswer)
            .where(
                quizAnswer.quiz.id.eq(quizId).and(
                    quizAnswer.totalScore.lt(cursorScore).run {
                        if (quizAnswerId != null) {
                            this.and(
                                quizAnswer.totalScore.lt(cursorScore).or(
                                    quizAnswer.totalScore.eq(cursorScore).and(quizAnswer.id.lt(quizAnswerId))
                                )
                            )
                        } else {
                            this
                        }
                    }
                )
            ).run {
                when (sortOrder) {
                    SortOrder.TOTAL_SCORE_DESC -> orderBy(quizAnswer.totalScore.desc(), quizAnswer.id.desc())
                }
            }
            .limit(size.toLong()).fetch()
    }

    override fun findQuizAnswersByQuizCreatorId(quizAnswerId: Long?, cursorScore: Int, quizCreatorId: Long, sortOrder: SortOrder, size: Int): List<QuizAnswer> {
        return jpaQueryFactory.select(quizAnswer)
            .from(quizAnswer)
            .join(quiz).fetchJoin()
            .where(
                quiz.user.id.eq(quizCreatorId).and(
                    quizAnswer.totalScore.lt(cursorScore).run {
                        if (quizAnswerId != null) {
                            this.and(
                                quizAnswer.totalScore.lt(cursorScore).or(
                                    quizAnswer.totalScore.eq(cursorScore).and(quizAnswer.id.lt(quizAnswerId))
                                )
                            )
                        } else {
                            this
                        }
                    }
                )
            )
            .run {
                when (sortOrder) {
                    SortOrder.TOTAL_SCORE_DESC -> orderBy(quizAnswer.totalScore.desc(), quizAnswer.id.desc())
                }
            }.fetch()
    }
}
