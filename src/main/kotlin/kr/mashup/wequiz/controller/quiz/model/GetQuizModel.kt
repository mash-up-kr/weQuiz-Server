package kr.mashup.wequiz.controller.quiz.model

import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.quiz.answer.Answer
import kr.mashup.wequiz.domain.quiz.question.Question

data class GetQuizResponse(
    val title: String,
    val questions: List<QuestionDto>
) {
    data class QuestionDto(
        val id: Long,
        val title: String,
        val priority: Int,
        val duplicatedAnswer: Boolean,
        val answers: List<AnswerDto>,
    ) {
        companion object {
            fun from(question: Question): QuestionDto {
                return QuestionDto(
                    id = question.id,
                    title = question.title,
                    priority = question.priority,
                    duplicatedAnswer = question.duplicatedAnswer,
                    answers = question.answers.map {
                        AnswerDto.from(it)
                    },
                )
            }
        }
    }

    data class AnswerDto(
        val id: Long,
        val content: String,
        val priority: Int,
        val correctAnswer: Boolean,
    ) {
        companion object {
            fun from(answer: Answer): AnswerDto {
                return AnswerDto(
                    id = answer.id,
                    content = answer.content,
                    priority = answer.priority,
                    correctAnswer = answer.correctAnswer,
                )
            }
        }
    }

    companion object {
        fun from(quiz: Quiz): GetQuizResponse {
            return GetQuizResponse(
                title = quiz.title,
                questions = quiz.questions.map {
                    QuestionDto.from(it)
                },
            )
        }
    }
}