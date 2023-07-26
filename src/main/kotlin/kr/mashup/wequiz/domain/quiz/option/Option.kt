package kr.mashup.wequiz.domain.quiz.option

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import kr.mashup.wequiz.domain.quiz.question.Question
import java.time.LocalDateTime

@Entity
@Table(name = "option1")
class Option(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    val question: Question,

    @Column(name = "content")
    val content: String,

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
            isCorrect: Boolean
        ): Option {
            return Option(
                question = question,
                content = content,
                isCorrect = isCorrect
            )
        }
    }
}
