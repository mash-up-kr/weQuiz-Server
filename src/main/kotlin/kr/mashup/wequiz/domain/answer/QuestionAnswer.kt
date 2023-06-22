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
    val user: User,

    @ManyToOne
    val quizAnswer: QuizAnswer,
) {

    companion object {
        private fun createNew(
            user: User,
            question: Question,
            option: Option,
            quizAnswer: QuizAnswer,
        ) = QuestionAnswer(
            user = user,
            question = question,
            option = option,
            quizAnswer = quizAnswer,
        )

        fun createNew(
            user: User,
            question: Question,
            options: List<Option>,
            quizAnswer: QuizAnswer,
        ) = options.map { createNew(user, question, it, quizAnswer) }
    }
}
