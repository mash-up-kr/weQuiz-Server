package kr.mashup.wequiz.application.quiz

import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.controller.quiz.model.CreateQuizRequest
import kr.mashup.wequiz.controller.quiz.model.GetQuizResponse
import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.quiz.option.Option
import kr.mashup.wequiz.domain.quiz.question.Question
import kr.mashup.wequiz.repository.quiz.QuizRepository
import kr.mashup.wequiz.repository.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuizService(
    private val userRepository: UserRepository,
    private val quizRepository: QuizRepository,
) {
    @Transactional
    fun createQuiz(
        userInfoDto: UserInfoDto,
        createQuizRequest: CreateQuizRequest,
    ): Quiz {
        val user = userRepository.findByIdOrNull(userInfoDto.id) ?: throw RuntimeException()
        val quiz = Quiz.createNew(
            user = user,
            title = createQuizRequest.title,
        )

        val questions = createQuizRequest.questions.map { questionDto ->
            val question = Question.createNew(
                quiz = quiz,
                title = questionDto.title,
                priority = questionDto.priority,
                duplicatedOption = questionDto.duplicatedOption,
            )

            val options = questionDto.options.map { optionDto ->
                Option.createNew(
                    question = question,
                    content = optionDto.content,
                    priority = optionDto.priority,
                    isCorrect = optionDto.isCorrect,
                )
            }
            question.also { it.setOptions(options) }
        }

        quiz.setQuestions(questions = questions)
        return quizRepository.save(quiz)
    }

    @Transactional(readOnly = true)
    fun getQuiz(quizId: Long): GetQuizResponse {
        val quiz = quizRepository.findByIdOrNull(quizId)?: throw IllegalArgumentException()
        return GetQuizResponse.from(quiz)
    }
}

