package kr.mashup.wequiz.domain.quiz

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import kr.mashup.wequiz.domain.quiz.question.Question
import kr.mashup.wequiz.domain.user.User
import java.time.LocalDateTime

@Entity
class Quiz(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "users_id")
    val user: User,

    @Column(name = "title")
    val title: String,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "quiz_id")
    val questions: MutableList<Question> = mutableListOf(),

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null
) {

    val isDelete = lazy { deletedAt != null }

    fun setQuestions(questions: List<Question>) {
        this.questions.addAll(questions)
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }

    fun isOwner(userId: Long): Boolean {
        return user.id == userId
    }

    fun findQuestion(questionId: Long): Question? {
        return questions.find { it.id == questionId }
    }

    companion object {
        fun createNew(
            user: User,
            title: String
        ): Quiz {
            return Quiz(
                user = user,
                title = title
            )
        }
    }
}
