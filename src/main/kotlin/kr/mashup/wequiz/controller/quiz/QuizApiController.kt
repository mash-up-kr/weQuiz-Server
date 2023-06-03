package kr.mashup.wequiz.controller.quiz

import kr.mashup.wequiz.application.quiz.QuizService
import kr.mashup.wequiz.config.auh.UserInfo
import kr.mashup.wequiz.config.auh.UserInfoDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/quiz")
class QuizApiController(
    private val quizService: QuizService,
) {

    @GetMapping("/{quizId}")
    fun getQuiz(
        @UserInfo userInfoDto: UserInfoDto,
        @PathVariable(name = "quizId") quizId: Long,
    ) {
    }
}