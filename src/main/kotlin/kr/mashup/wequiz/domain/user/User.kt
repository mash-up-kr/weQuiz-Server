package kr.mashup.wequiz.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import kr.mashup.wequiz.domain.quiz.Quiz

@Table(name = "users")
@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "token")
    val token: String,

    @Column(name = "phone")
    val phone: String,

    @Column(name = "nickname")
    val nickname: String,

    @Column(name = "description")
    val description: String,

    @OneToMany(mappedBy = "user")
    val quiz: List<Quiz> = emptyList()
) {

    companion object {
        fun createNew(
            token: String,
            phone: String,
            nickname: String,
            description: String
        ): User {
            return User(
                token = token,
                phone = phone,
                nickname = nickname,
                description = description
            )
        }
    }
}
