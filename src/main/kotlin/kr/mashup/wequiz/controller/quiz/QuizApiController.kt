package kr.mashup.wequiz.controller.quiz

import kr.mashup.wequiz.application.quiz.QuizService
import kr.mashup.wequiz.config.auh.UserInfo
import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.controller.quiz.model.*
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/quiz")
class QuizApiController(
    private val quizService: QuizService
) {
    @PostMapping
    fun createQuiz(
        @UserInfo userInfoDto: UserInfoDto,
        @RequestBody createQuizRequest: CreateQuizRequest
    ): CreateQuizResponse {
        return CreateQuizResponse(quizId = quizService.createQuiz(userInfoDto, createQuizRequest).id)
    }

    @GetMapping("/{quizId}")
    fun getQuiz(
        @UserInfo userInfoDto: UserInfoDto,
        @PathVariable(name = "quizId") quizId: Long
    ): GetQuizResponse {
        return quizService.getQuiz(quizId)
    }

    @GetMapping
    fun getQuizList(
        @UserInfo userInfoDto: UserInfoDto,
        @RequestParam size: Int = 15,
        @RequestParam cursor: Long? = null,
    ): GetQuizListResponse {
        val quizList = quizService.getQuizList(cursor, size)
        return GetQuizListResponse.from(quizList)
    }

    @DeleteMapping("/{quizId}")
    fun deleteQuiz(
        @UserInfo userInfoDto: UserInfoDto,
        @PathVariable(name = "quizId") quizId: Long
    ): DeleteQuizResponse {
        quizService.deleteQuiz(
            requesterId = userInfoDto.id,
            quizId = quizId
        )
        return DeleteQuizResponse(isDeleted = true)
    }
}
