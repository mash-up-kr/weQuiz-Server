package kr.mashup.wequiz.domain.quiz

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import kr.mashup.wequiz.domain.quiz.question.Question

@Entity
class Quiz(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "title")
    val title: String,

    @OneToMany
    @JoinColumn()
    val questions: List<Question>
) {
}