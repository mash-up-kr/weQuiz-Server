package kr.mashup.wequiz.domain.answer

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.user.User
import java.time.LocalDateTime

@Entity(name = "quiz_answer")
class QuizAnswer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    val quiz: Quiz,

    @Column(name = "total_score")
    var totalScore: Int = 0,

    @ManyToOne
    val user: User,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "quiz_answer_id")
    val questionAnswers: MutableList<QuestionAnswer> = mutableListOf(),

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null
) {
    fun setQuestionAnswers(questionAnswers: List<QuestionAnswer>) {
        this.questionAnswers.addAll(questionAnswers)
    }

    companion object {
        fun createNew(
            user: User,
            quiz: Quiz
        ) = QuizAnswer(
            user = user,
            quiz = quiz
        )
    }
}
