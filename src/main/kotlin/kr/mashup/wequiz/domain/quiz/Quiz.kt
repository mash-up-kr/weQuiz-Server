package kr.mashup.wequiz.domain.quiz

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import kr.mashup.wequiz.domain.quiz.question.Question
import kr.mashup.wequiz.domain.user.User

@Entity
class Quiz(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(name = "title")
    val title: String,

    @OneToMany
    @JoinColumn(name = "quiz_id")
    val questions: List<Question>,

    @Column(name = "is_delete")
    val isDelete: Boolean,
)