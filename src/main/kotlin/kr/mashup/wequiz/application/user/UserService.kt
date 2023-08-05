package kr.mashup.wequiz.application.user

import kr.mashup.wequiz.domain.exception.WeQuizError
import kr.mashup.wequiz.domain.exception.WeQuizException
import kr.mashup.wequiz.domain.user.User
import kr.mashup.wequiz.repository.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

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
            throw WeQuizException(WeQuizError.WEC500)
        }

        val user = User.createNew(
            token = token,
            phone = phone,
            nickname = nickname,
            description = description
        )
        userRepository.save(user)
    }

    fun joinAnonymous(nickname: String): User {
        val randomToken = UUID.randomUUID().toString()

        val user = User.createAnonymous(
            token = randomToken,
            nickname = nickname
        )
        return userRepository.save(user)
    }
    fun find(id: Long) = userRepository.findByIdOrNull(id) ?: throw RuntimeException()
}
