package kr.mashup.wequiz.domain.quiz.question

import jakarta.persistence.*
import kr.mashup.wequiz.domain.quiz.Quiz
import kr.mashup.wequiz.domain.quiz.option.Option

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

    @Column(name = "is_duplicated_Option")
    val duplicatedOption: Boolean,

    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL])
    val options: MutableList<Option> = mutableListOf(),
) {
    fun setOptions(Options: List<Option>) {
        this.options.addAll(Options)
    }

    companion object {
        fun createNew(
            quiz: Quiz,
            title: String,
            priority: Int,
            duplicatedOption: Boolean,
        ): Question {
            return Question(
                quiz = quiz,
                title = title,
                priority = priority,
                duplicatedOption = duplicatedOption,
            )
        }
    }
}