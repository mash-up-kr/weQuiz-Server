package kr.mashup.wequiz.domain.answer

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.user.User

@Entity(name = "quiz_answer")
class QuizAnswer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    val quiz: Quiz,

    @Column(name = "total_score")
    val totalScore: Int,

    @ManyToOne
    val user: User
) {
    companion object {
        fun createNew(
            user: User,
            quiz: Quiz,
            totalScore: Int
        ) = QuizAnswer(
            user = user,
            quiz = quiz,
            totalScore = totalScore
        )
    }
}
