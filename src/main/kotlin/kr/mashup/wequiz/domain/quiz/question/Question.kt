package kr.mashup.wequiz.domain.quiz.question

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
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

    @Column(name = "score")
    val score: Int,

    @Column(name = "is_duplicated_Option")
    val duplicatedOption: Boolean,

    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL])
    val options: MutableList<Option> = mutableListOf()
) {
    fun setOptions(Options: List<Option>) {
        this.options.addAll(Options)
    }

    fun findOption(optionId: Long): Option? {
        return options.find { it.id == optionId }
    }

    companion object {
        fun createNew(
            quiz: Quiz,
            title: String,
            priority: Int,
            score: Int,
            duplicatedOption: Boolean
        ): Question {
            return Question(
                quiz = quiz,
                title = title,
                priority = priority,
                score = score,
                duplicatedOption = duplicatedOption
            )
        }
    }
}
