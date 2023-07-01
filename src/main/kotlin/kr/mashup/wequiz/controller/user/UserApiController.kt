package kr.mashup.wequiz.controller.user

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.mashup.wequiz.application.user.UserService
import kr.mashup.wequiz.controller.user.model.JoinUserRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "유저 API", description = "UserApiController")
@RestController
@RequestMapping("/api/v1/user")
class UserApiController(
    private val userService: UserService
) {
    @Operation(summary = "회원 가입")
    @PostMapping("/join")
    fun joinUser(
        @RequestBody joinUserRequest: JoinUserRequest
    ) {
        userService.join(
            token = joinUserRequest.token,
            phone = joinUserRequest.phone,
            nickname = joinUserRequest.nickname,
            description = joinUserRequest.description
        )
    }
}
