package kr.mashup.wequiz.domain.answer

import jakarta.persistence.*
import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.user.User

@Entity(name = "quiz_answer")
class QuizAnswer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    val quiz: Quiz,

    @Column(name = "total_score")
    var totalScore: Int = 0,

    @ManyToOne
    val user: User,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "quiz_answer_id")
    val questionAnswers: MutableList<QuestionAnswer> = mutableListOf(),
) {
    fun setQuestionAnswers(questionAnswers: List<QuestionAnswer>) {
        this.questionAnswers.addAll(questionAnswers)
    }

    companion object {
        fun createNew(
            user: User,
            quiz: Quiz,
        ) = QuizAnswer(
            user = user,
            quiz = quiz,
        )
    }
}
