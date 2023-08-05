package kr.mashup.wequiz.application.answer

import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.domain.answer.QuestionAnswer
import kr.mashup.wequiz.domain.answer.QuestionAnswerScoreCalculator
import kr.mashup.wequiz.domain.answer.QuizAnswer
import kr.mashup.wequiz.domain.exception.WeQuizException
import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.user.User
import kr.mashup.wequiz.repository.answer.QuizAnswerRepository
import kr.mashup.wequiz.repository.quiz.QuizRepository
import kr.mashup.wequiz.repository.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AnswerService(
    private val userRepository: UserRepository,
    private val quizRepository: QuizRepository,
    private val quizAnswerRepository: QuizAnswerRepository,
    private val questionAnswerScoreCalculator: QuestionAnswerScoreCalculator
) {
    @Transactional
    fun create(
        userInfo: UserInfoDto,
        quizId: Long,
        answers: List<AnswersDto>
    ): QuizAnswer {
        val user = userRepository.findByIdOrNull(userInfo.id) ?: throw WeQuizException("유저를 찾을 수 없어요.")
        val quiz = quizRepository.findByIdOrNull(quizId) ?: throw WeQuizException("퀴즈를 찾을 수 없어요.")

        if(quiz.isOwner(user.id)) throw WeQuizException("내가 만든 퀴즈는 풀 수 없어요.")
        val quizAnswer = createQuizAnswer(quizId, user, quiz)

        val totalScore = answers.sumOf { answer ->
            val question = quiz.findQuestion(answer.questionId) ?: throw WeQuizException("문제를 찾을 수 없어요.")
            val options = answer.optionIds.map { optionId ->
                question.findOption(optionId) ?: throw WeQuizException("옵션을 찾을 수 없어요.")
            }

            val questionAnswers = QuestionAnswer.createNew(
                user = user,
                question = question,
                options = options,
                quizAnswer = quizAnswer
            )

            quizAnswer.setQuestionAnswers(questionAnswers)
            questionAnswerScoreCalculator.calculateScore(questionAnswers)
        }

        quizAnswer.totalScore = totalScore

        return quizAnswerRepository.save(quizAnswer)
    }

    private fun createQuizAnswer(quizId: Long, user: User, quiz: Quiz): QuizAnswer {
        quizAnswerRepository.findByQuizIdAndUserId(quizId, user.id)?.let { quizAnswer ->
            quizAnswerRepository.delete(quizAnswer)
        }

        return QuizAnswer.createNew(
            user = user,
            quiz = quiz
        )
    }
}

data class AnswersDto(
    val questionId: Long,
    val optionIds: List<Long>
)

data class QuizAnswerRankingDto(
    val userId: Long,
    val nickname: String,
    val totalScore: Int,
    val quizAnswerId: Long
)

fun QuizAnswer.toRankingDto() = QuizAnswerRankingDto(
    userId = this.user.id,
    nickname = this.user.nickname,
    totalScore = this.totalScore,
    quizAnswerId = this.id
)
