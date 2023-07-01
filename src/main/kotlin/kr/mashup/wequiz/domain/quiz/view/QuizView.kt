package kr.mashup.wequiz.domain.quiz.view

import kr.mashup.wequiz.domain.quiz.Quiz

data class QuizView(
    val id: Long,
    val title: String,
    val questions: List<QuestionView>
) {
    companion object {
        fun from(quiz: Quiz): QuizView {
            return QuizView(
                id = quiz.id,
                title = quiz.title,
                questions = quiz.questions.map { question ->
                    QuestionView(
                        id = question.id,
                        priority = question.priority,
                        title = question.title,
                        options = question.options.map { option ->
                            OptionView(
                                id = option.id,
                                priority = option.priority,
                                content = option.content,
                            )
                        },
                    )
                },
            )
        }
    }
}

data class QuestionView(
    val id: Long,
    val priority: Int,
    val title: String,
    val options: List<OptionView>,
)

data class OptionView(
    val id: Long,
    val priority: Int,
    val content: String,
)