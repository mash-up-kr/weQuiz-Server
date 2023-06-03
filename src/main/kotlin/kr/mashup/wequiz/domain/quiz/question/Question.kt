package kr.mashup.wequiz.domain.quiz.question

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.quiz.answer.Answer

@Entity
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    val quiz: Quiz,

    @Column(name = "title")
    val title: String,

    @Column(name = "order")
    val order: Int,

    @Column(name = "is_duplicated_answer")
    val duplicatedAnswer: Boolean,

    @OneToMany(mappedBy = "question")
    val answers: List<Answer>,
)