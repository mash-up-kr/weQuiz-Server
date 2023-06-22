package kr.mashup.wequiz.controller.answer

import kr.mashup.wequiz.application.answer.AnswerService
import kr.mashup.wequiz.application.answer.AnswersDto
import kr.mashup.wequiz.config.auh.UserInfo
import kr.mashup.wequiz.config.auh.UserInfoDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/quiz/{quizId}/answers")
class AnswerApiController(
    private val answerService: AnswerService
) {

    @PostMapping
    fun createAnswers(
        @UserInfo userInfoDto: UserInfoDto,
        @PathVariable quizId: Long,
        @RequestBody createAnswerForm: CreateAnswerForm
    ): CreateAnswerResponse {
        val quizAnswer = answerService.create(
            userInfo = userInfoDto,
            quizId = quizId,
            answers = createAnswerForm.answers
        )

        return CreateAnswerResponse(
            quizCreator = UserInfoDto.from(quizAnswer.quiz.user),
            quizResolver = userInfoDto,
            totalScore = quizAnswer.totalScore
        )
    }
}

data class CreateAnswerForm(
    val answers: List<AnswersDto>
)

data class CreateAnswerResponse(
    val quizCreator: UserInfoDto,
    val quizResolver: UserInfoDto,
    val totalScore: Int
)
