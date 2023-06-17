package kr.mashup.wequiz.controller.user

import kr.mashup.wequiz.application.user.UserService
import kr.mashup.wequiz.controller.user.model.JoinUserRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserApiController(
    private val userService: UserService
) {
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
