package kr.mashup.wequiz.domain.quiz.option

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import kr.mashup.wequiz.domain.quiz.question.Question
import java.time.LocalDateTime

@Entity
class Option(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    val question: Question,

    @Column(name = "content")
    val content: String,

    @Column(name = "priority")
    val priority: Int,

    @Column(name = "is_correct")
    val isCorrect: Boolean,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null
) {
    companion object {
        fun createNew(
            question: Question,
            content: String,
            priority: Int,
            isCorrect: Boolean
        ): Option {
            return Option(
                question = question,
                content = content,
                priority = priority,
                isCorrect = isCorrect
            )
        }
    }
}
