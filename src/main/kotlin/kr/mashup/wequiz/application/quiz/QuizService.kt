package kr.mashup.wequiz.application.quiz

import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.controller.quiz.model.CreateQuizRequest
import kr.mashup.wequiz.controller.quiz.model.GetQuizResponse
import kr.mashup.wequiz.domain.exception.WeQuizException
import kr.mashup.wequiz.domain.quiz.QuestionScoreCalculator
import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.quiz.option.Option
import kr.mashup.wequiz.domain.quiz.question.Question
import kr.mashup.wequiz.repository.quiz.QuizRepository
import kr.mashup.wequiz.repository.user.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuizService(
    private val userRepository: UserRepository,
    private val quizRepository: QuizRepository,
    private val questionsScoreCalculator: QuestionScoreCalculator
) {
    @Transactional
    fun createQuiz(
        userInfoDto: UserInfoDto,
        createQuizRequest: CreateQuizRequest
    ): Quiz {
        val user = userRepository.findByIdOrNull(userInfoDto.id) ?: throw WeQuizException()
        val quiz = Quiz.createNew(
            user = user,
            title = createQuizRequest.title
        )

        val scores = questionsScoreCalculator.calculateScores(createQuizRequest.questions)
        val questions = createQuizRequest.questions.mapIndexed { index, questionDto ->
            val question = Question.createNew(
                quiz = quiz,
                title = questionDto.title,
                score = scores[index],
                duplicatedOption = questionDto.duplicatedOption
            )

            val options = questionDto.options.map { optionDto ->
                Option.createNew(
                    question = question,
                    content = optionDto.content,
                    isCorrect = optionDto.isCorrect
                )
            }
            question.also { it.setOptions(options) }
        }

        quiz.setQuestions(questions = questions)
        return quizRepository.save(quiz)
    }

    @Transactional(readOnly = true)
    fun getQuiz(quizId: Long): GetQuizResponse {
        val quiz = quizRepository.findByIdOrNull(quizId) ?: throw WeQuizException("퀴즈를 찾을 수 없어요.")
        return GetQuizResponse.from(quiz)
    }

    @Transactional(readOnly = true)
    fun getQuizList(userId: Long, cursor: Long?, size: Int): List<Quiz> {
        return if (cursor != null) {
            quizRepository.findAllByIdAndUserIdBeforeAndDeletedAtIsNullOrderByIdDesc(
                id = cursor,
                userId = userId,
                pageable = PageRequest.of(0, size)
            )
        } else {
            quizRepository.findAllByUserIdAndDeletedAtIsNullOrderByIdDesc(
                userId = userId,
                pageable = PageRequest.of(0, size)
            )
        }
    }

    @Transactional
    fun deleteQuiz(requesterId: Long, quizId: Long) {
        val quiz = quizRepository.findByIdOrNull(quizId) ?: throw WeQuizException()
        if (!quiz.isOwner(requesterId)) {
            throw RuntimeException("본인의 퀴즈만 삭제 할 수 있어요")
        }
        quiz.delete()
    }
}
