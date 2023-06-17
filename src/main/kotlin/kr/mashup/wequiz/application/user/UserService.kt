package kr.mashup.wequiz.application.user

import kr.mashup.wequiz.domain.user.User
import kr.mashup.wequiz.repository.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun join(
        token: String,
        phone: String,
        nickname: String,
        description: String
    ) {
        val isExists = userRepository.existsByToken(token)

        if (isExists) {
            throw RuntimeException()
        }

        val user = User.createNew(
            token = token,
            phone = phone,
            nickname = nickname,
            description = description
        )
        userRepository.save(user)
    }
}
