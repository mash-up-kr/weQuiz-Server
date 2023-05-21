package kr.mashup.wequiz.application.user

import kr.mashup.wequiz.domain.user.User
import kr.mashup.wequiz.repository.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun join(
        token: String,
        phone: String,
        nickname: String,
        description: String,
    ) {
        //TODO 폰번호 중복 체크, validation etc ,,,

        val user = User.createNew(
            token = token,
            phone = phone,
            nickname = nickname,
            description = description,
        )
        userRepository.save(user)
    }
}