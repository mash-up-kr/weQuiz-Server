// ktlint-disable filename
package kr.mashup.wequiz.controller.quiz.model

import kr.mashup.wequiz.domain.quiz.Quiz

data class GetQuizListResponse(
    val quiz: List<QuizDto>,
    val nextCursor: Long?
) {
    data class QuizDto(
        val id: Long,
        val title: String
    )

    companion object {
        fun from(quizList: List<Quiz>): GetQuizListResponse {
            return GetQuizListResponse(
                quiz = quizList.map {
                    QuizDto(
                        id = it.id,
                        title = it.title
                    )
                },
                nextCursor = quizList.lastOrNull()?.id
            )
        }
    }
}
