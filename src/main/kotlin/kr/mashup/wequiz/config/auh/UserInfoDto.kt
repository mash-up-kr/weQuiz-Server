package kr.mashup.wequiz.config.auh

import kr.mashup.wequiz.domain.user.User

class UserInfoDto(
    val id: Long,
    val name: String
) {
    companion object {
        fun from(user: User) = UserInfoDto(
            id = user.id,
            name = user.nickname
        )
    }
}
