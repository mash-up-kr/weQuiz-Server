package kr.mashup.wequiz.controller.quiz

import kr.mashup.wequiz.application.quiz.QuizService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/quiz")
class QuizApiController(
    private val quizService: QuizService,
) {

}