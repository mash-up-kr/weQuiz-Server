package kr.mashup.wequiz.controller.quiz.model

data class CreateQuizRequest(
    val title: String,
    val questions: List<QuestionDto>,
)

data class QuestionDto(
    val title: String,
    val priority: Int,
    val duplicatedAnswer: Boolean,
    val answers: List<AnswerDto>,
)

data class AnswerDto(
    val content: String,
    val priority: Int,
    val correctAnswer: Boolean,
)

data class CreateQuizResponse(
    val quizId: Long,
)