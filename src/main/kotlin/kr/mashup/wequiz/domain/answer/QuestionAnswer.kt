package kr.mashup.wequiz.domain.answer

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import kr.mashup.wequiz.domain.quiz.option.Option
import kr.mashup.wequiz.domain.quiz.question.Question
import kr.mashup.wequiz.domain.user.User

@Entity(name = "question_answer")
class QuestionAnswer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    val question: Question,

    @ManyToOne
    val option: Option,

    @ManyToOne
    val user: User
) {

    fun calculateScore() = if (option.isCorrect) question.score else 0

    companion object {
        private fun createNew(
            user: User,
            question: Question,
            option: Option
        ) = QuestionAnswer(
            user = user,
            question = question,
            option = option
        )

        fun createNew(
            user: User,
            question: Question,
            options: List<Option>
        ) = options.map { createNew(user, question, it) }
    }
}