package kr.mashup.wequiz.application.user

import kr.mashup.wequiz.repository.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
}