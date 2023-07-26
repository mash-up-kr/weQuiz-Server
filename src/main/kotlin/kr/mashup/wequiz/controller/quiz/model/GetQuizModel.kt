// ktlint-disable filename
package kr.mashup.wequiz.controller.quiz.model

import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.quiz.option.Option
import kr.mashup.wequiz.domain.quiz.question.Question

data class GetQuizResponse(
    val id: Long,
    val title: String,
    val questions: List<QuestionPresentation>
) {
    data class QuestionPresentation(
        val id: Long,
        val title: String,
        val score: Int,
        val answerCounts: Int,
        val options: List<OptionPresentation>
    ) {
        companion object {
            fun from(question: Question): QuestionPresentation {
                return QuestionPresentation(
                    id = question.id,
                    title = question.title,
                    score = question.score,
                    answerCounts = question.getAnswersCount(),
                    options = question.options.map {
                        OptionPresentation.from(it)
                    }
                )
            }
        }
    }

    data class OptionPresentation(
        val id: Long,
        val content: String,
        val isCorrect: Boolean
    ) {
        companion object {
            fun from(option: Option): OptionPresentation {
                return OptionPresentation(
                    id = option.id,
                    content = option.content,
                    isCorrect = option.isCorrect
                )
            }
        }
    }

    companion object {
        fun from(quiz: Quiz): GetQuizResponse {
            return GetQuizResponse(
                id = quiz.id,
                title = quiz.title,
                questions = quiz.questions.map {
                    QuestionPresentation.from(it)
                }
            )
        }
    }
}
