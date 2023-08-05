package kr.mashup.wequiz.controller.answer

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import kr.mashup.wequiz.application.answer.AnswerService
import kr.mashup.wequiz.application.answer.AnswersDto
import kr.mashup.wequiz.config.auh.UserInfo
import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.controller.ApiResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "문제 풀이 API", description = "AnswerApiController")
@RestController
@RequestMapping("/api/v1/quiz/{quizId}/answers")
class AnswerApiController(
    private val answerService: AnswerService
) {

    @Operation(summary = "문제 풀이")
    @PostMapping
    fun createAnswers(
        @Schema(hidden = true) @UserInfo userInfoDto: UserInfoDto,
        @PathVariable quizId: Long,
        @RequestBody createAnswerForm: CreateAnswerForm
    ): ApiResponse<CreateAnswerResponse> {
        val quizAnswer = answerService
            .create(
                userInfo = userInfoDto,
                quizId = quizId,
                answers = createAnswerForm.answers
            )

        return CreateAnswerResponse(
            quizCreator = UserInfoDto.from(quizAnswer.quiz.user),
            quizResolver = userInfoDto,
            totalScore = quizAnswer.totalScore
        ).let { ApiResponse.success(it) }
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
