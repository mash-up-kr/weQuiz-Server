package kr.mashup.wequiz.controller.quiz

import kr.mashup.wequiz.application.quiz.QuizService
import kr.mashup.wequiz.config.auh.UserInfo
import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.controller.quiz.model.CreateQuizRequest
import kr.mashup.wequiz.controller.quiz.model.CreateQuizResponse
import kr.mashup.wequiz.controller.quiz.model.DeleteQuizResponse
import kr.mashup.wequiz.controller.quiz.model.GetQuizResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/quiz")
class QuizApiController(
    private val quizService: QuizService,
) {
    @PostMapping
    fun createQuiz(
        @UserInfo userInfoDto: UserInfoDto,
        @RequestBody createQuizRequest: CreateQuizRequest,
    ): CreateQuizResponse {
        return CreateQuizResponse(quizId = quizService.createQuiz(userInfoDto, createQuizRequest).id)
    }

    @GetMapping("/{quizId}")
    fun getQuiz(
        @UserInfo userInfoDto: UserInfoDto,
        @PathVariable(name = "quizId") quizId: Long,
    ): GetQuizResponse {
        return quizService.getQuiz(quizId)
    }

    @DeleteMapping("/{quizId}")
    fun deleteQuiz(
        @UserInfo userInfoDto: UserInfoDto,
        @PathVariable(name = "quizId") quizId: Long,
    ): DeleteQuizResponse {
        quizService.deleteQuiz(
            requesterId = userInfoDto.id,
            quizId = quizId,
        )
        return DeleteQuizResponse(isDeleted = true)
    }
}