package kr.mashup.wequiz.domain.quiz.question

import jakarta.persistence.*
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

    @Column(name = "priority")
    val priority: Int,

    @Column(name = "is_duplicated_answer")
    val duplicatedAnswer: Boolean,

    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL])
    val answers: MutableList<Answer> = mutableListOf(),
) {
    fun setAnswers(answers: List<Answer>) {
        this.answers.addAll(answers)
    }

    companion object {
        fun createNew(
            quiz: Quiz,
            title: String,
            priority: Int,
            duplicatedAnswer: Boolean,
        ): Question {
            return Question(
                quiz = quiz,
                title = title,
                priority = priority,
                duplicatedAnswer = duplicatedAnswer,
            )
        }
    }
}