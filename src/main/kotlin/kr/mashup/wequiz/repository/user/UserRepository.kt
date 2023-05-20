package kr.mashup.wequiz.repository.user

import kr.mashup.wequiz.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}