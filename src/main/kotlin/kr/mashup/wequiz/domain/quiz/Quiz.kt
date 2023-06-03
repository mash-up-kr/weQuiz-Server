package kr.mashup.wequiz.domain.quiz

import jakarta.persistence.*
import kr.mashup.wequiz.domain.quiz.question.Question
import kr.mashup.wequiz.domain.user.User

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

    @Column(name = "is_delete")
    val isDelete: Boolean,
) {
    fun setQuestions(questions: List<Question>) {
        this.questions.addAll(questions)
    }

    companion object {
        fun createNew(
            user: User,
            title: String,
        ): Quiz {
            return Quiz(
                user = user,
                title = title,
                isDelete = false,
            )
        }
    }
}