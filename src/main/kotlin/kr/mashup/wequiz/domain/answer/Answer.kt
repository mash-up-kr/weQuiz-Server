package kr.mashup.wequiz.domain.answer

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.quiz.option.Option
import kr.mashup.wequiz.domain.quiz.question.Question
import kr.mashup.wequiz.domain.user.User

@Entity
class Answer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    val user: User,

    @ManyToOne
    val quiz: Quiz,

    @ManyToOne
    val question: Question,

    @ManyToOne
    val option: Option
) {
companion object {
        private fun createNew(
            user: User,
            quiz: Quiz,
            question: Question,
            option: Option
        ): Answer {
            return Answer(
                user = user,
                quiz = quiz,
                question = question,
                option = option
            )
        }

        fun createNew(
            user: User,
            quiz: Quiz,
            question: Question,
            options: List<Option>
        ) = options.map { createNew(user, quiz, question, it) }
    }
}