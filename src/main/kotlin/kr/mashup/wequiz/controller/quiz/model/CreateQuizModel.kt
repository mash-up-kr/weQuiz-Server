package kr.mashup.wequiz.controller.quiz.model

data class CreateQuizRequest(
    val title: String,
    val questions: List<QuestionDto>
)

data class QuestionDto(
    val title: String,
    val duplicatedOption: Boolean,
    val options: List<OptionDto>
)

data class OptionDto(
    val content: String,
    val isCorrect: Boolean
)

data class CreateQuizResponse(
    val quizId: Long
)
