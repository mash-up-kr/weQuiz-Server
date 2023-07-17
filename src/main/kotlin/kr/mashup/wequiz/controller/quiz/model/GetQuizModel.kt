// ktlint-disable filename
package kr.mashup.wequiz.controller.quiz.model

import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.quiz.option.Option
import kr.mashup.wequiz.domain.quiz.question.Question

data class GetQuizResponse(
    val title: String,
    val questions: List<QuestionDto>
) {
    data class QuestionDto(
        val id: Long,
        val title: String,
        val order: Int,
        val score: Int,
        val answerCounts: Int,
        val options: List<OptionDto>
    ) {
        companion object {
            fun from(question: Question): QuestionDto {
                return QuestionDto(
                    id = question.id,
                    title = question.title,
                    order = question.priority,
                    score = question.score,
                    answerCounts = question.getAnswersCount(),
                    options = question.options.map {
                        OptionDto.from(it)
                    }
                )
            }
        }
    }

    data class OptionDto(
        val id: Long,
        val content: String,
        val order: Int,
        val isCorrect: Boolean
    ) {
        companion object {
            fun from(option: Option): OptionDto {
                return OptionDto(
                    id = option.id,
                    content = option.content,
                    order = option.priority,
                    isCorrect = option.isCorrect
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
                }
            )
        }
    }
}
