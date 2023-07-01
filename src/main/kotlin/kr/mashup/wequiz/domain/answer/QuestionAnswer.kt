package kr.mashup.wequiz.domain.answer

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import kr.mashup.wequiz.domain.quiz.option.Option
import kr.mashup.wequiz.domain.quiz.question.Question
import kr.mashup.wequiz.domain.user.User
import java.time.LocalDateTime

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

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null
) {

    companion object {
        private fun createNew(
            user: User,
            question: Question,
            option: Option,
            quizAnswer: QuizAnswer
        ) = QuestionAnswer(
            user = user,
            question = question,
            option = option,
            quizAnswer = quizAnswer
        )

        fun createNew(
            user: User,
            question: Question,
            options: List<Option>,
            quizAnswer: QuizAnswer
        ) = options.map { createNew(user, question, it, quizAnswer) }
    }
}
