package kr.mashup.wequiz.domain.quiz.answer

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import kr.mashup.wequiz.domain.quiz.question.Question

@Entity
class Answer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    val question: Question,

    @Column(name = "content")
    val content: String,

    @Column(name = "order")
    val order: Int,

    @Column(name = "correct_answer")
    val correctAnswer: Boolean,
)