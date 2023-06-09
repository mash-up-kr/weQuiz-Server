package kr.mashup.wequiz.application.answer

import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.domain.answer.Answer
import kr.mashup.wequiz.repository.quiz.QuizRepository
import kr.mashup.wequiz.repository.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AnswerService(
    private val userRepository: UserRepository,
    private val quizRepository: QuizRepository,
) {

    @Transactional
    fun create(
        userInfo : UserInfoDto,
        quizId: Long,
        answers: List<AnswersDto>,
    ): List<Answer> {
        val user = userRepository.findByIdOrNull(userInfo.id) ?: throw IllegalArgumentException("유저를 찾을 수 없어요.")
        val quiz = quizRepository.findByIdOrNull(quizId) ?: throw IllegalArgumentException("퀴즈를 찾을 수 없어요.")

        return answers.map { answer ->
            val question = quiz.findQuestion(answer.questionId) ?: throw IllegalArgumentException("문제를 찾을 수 없어요.")
            val options = answer.optionIds.map { optionId ->
                question.findOption(optionId) ?: throw IllegalArgumentException("옵션을 찾을 수 없어요.")
            }
            Answer.createNew(
                user = user,
                quiz = quiz,
                question = question,
                options = options,
            )
        }.flatten()
    }
}

data class AnswersDto(
    val questionId: Long,
    val optionIds: List<Long>
)