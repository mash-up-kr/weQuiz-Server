package kr.mashup.wequiz.controller.user.dto

data class JoinUserRequest(
    val token: String,
    val phone: String,
    val nickname: String,
    val description: String,
)