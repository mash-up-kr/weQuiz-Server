package kr.mashup.wequiz.controller.quiz

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import kr.mashup.wequiz.application.quiz.QuizService
import kr.mashup.wequiz.config.auh.UserInfo
import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.controller.quiz.model.CreateQuizRequest
import kr.mashup.wequiz.controller.quiz.model.CreateQuizResponse
import kr.mashup.wequiz.controller.quiz.model.DeleteQuizResponse
import kr.mashup.wequiz.controller.quiz.model.GetQuizListResponse
import kr.mashup.wequiz.controller.quiz.model.GetQuizResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "퀴즈 API", description = "QuizApiController")
@RestController
@RequestMapping("/api/v1/quiz")
class QuizApiController(
    private val quizService: QuizService
) {
    @Operation(summary = "퀴즈 생성")
    @PostMapping
    fun createQuiz(
        @Schema(hidden = true) @UserInfo userInfoDto: UserInfoDto,
        @RequestBody createQuizRequest: CreateQuizRequest
    ): CreateQuizResponse {
        return CreateQuizResponse(quizId = quizService.createQuiz(userInfoDto, createQuizRequest).id)
    }

    @Operation(summary = "퀴즈 단건 조회")
    @GetMapping("/{quizId}")
    fun getQuiz(
        @Schema(hidden = true) @UserInfo userInfoDto: UserInfoDto,
        @PathVariable(name = "quizId") quizId: Long
    ): GetQuizResponse {
        return quizService.getQuiz(quizId)
    }

    @Operation(
        summary = "퀴즈 리스트 조회 (커서 페이징)",
        description = "- size 는 default 15 \n " +
            "- cursor 를 넘기지 않으면 첫 페이지 \n " +
            "- nextCursor 를 다시 넣어서 조회"
    )
    @GetMapping
    fun getQuizList(
        @Schema(hidden = true) @UserInfo userInfoDto: UserInfoDto,
        @RequestParam size: Int = 15,
        @RequestParam cursor: Long? = null
    ): GetQuizListResponse {
        val quizList = quizService.getQuizList(cursor, size)
        return GetQuizListResponse.from(quizList)
    }

    @Operation(summary = "퀴즈 삭제")
    @DeleteMapping("/{quizId}")
    fun deleteQuiz(
        @Schema(hidden = true) @UserInfo userInfoDto: UserInfoDto,
        @PathVariable(name = "quizId") quizId: Long
    ): DeleteQuizResponse {
        quizService.deleteQuiz(
            requesterId = userInfoDto.id,
            quizId = quizId
        )
        return DeleteQuizResponse(isDeleted = true)
    }
}
