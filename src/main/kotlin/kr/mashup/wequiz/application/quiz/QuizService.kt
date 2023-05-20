package kr.mashup.wequiz.application.quiz

import kr.mashup.wequiz.repository.quiz.QuizRepository
import org.springframework.stereotype.Service

@Service
class QuizService(
    private val quizRepository: QuizRepository,
) {
}