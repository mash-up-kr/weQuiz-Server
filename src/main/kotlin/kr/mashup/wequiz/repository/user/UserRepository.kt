package kr.mashup.wequiz.repository.user

import kr.mashup.wequiz.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByToken(token: String): User?
    fun existsByToken(token: String): Boolean
}
