package kr.mashup.wequiz.controller.user.model

data class JoinUserRequest(
    val token: String,
    val phone: String,
    val nickname: String,
    val description: String,
)