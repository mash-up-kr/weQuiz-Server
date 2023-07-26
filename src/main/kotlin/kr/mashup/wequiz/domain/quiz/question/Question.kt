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
import kr.mashup.wequiz.lib.toInt
import java.time.LocalDateTime

@Entity
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    val quiz: Quiz,

    @Column(name = "title")
    val title: String,

    @Column(name = "score")
    val score: Int,

    @Column(name = "is_duplicated_Option")
    val duplicatedOption: Boolean,

    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL])
    val options: MutableList<Option> = mutableListOf(),

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null
) {

    fun getAnswersCount(): Int {
        return options.sumOf { it.isCorrect.toInt() }
    }

    fun setOptions(options: List<Option>) {
        this.options.addAll(options)
    }

    fun findOption(optionId: Long): Option? {
        return options.find { it.id == optionId }
    }

    companion object {
        fun createNew(
            quiz: Quiz,
            title: String,
            score: Int,
            duplicatedOption: Boolean
        ): Question {
            return Question(
                quiz = quiz,
                title = title,
                score = score,
                duplicatedOption = duplicatedOption
            )
        }
    }
}
