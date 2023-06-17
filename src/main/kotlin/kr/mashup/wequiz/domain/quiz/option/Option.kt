package kr.mashup.wequiz.domain.quiz.option

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import kr.mashup.wequiz.domain.quiz.question.Question

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
    val isCorrect: Boolean
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
