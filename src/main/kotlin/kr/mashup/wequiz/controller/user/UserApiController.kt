package kr.mashup.wequiz.controller.user

import kr.mashup.wequiz.application.user.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserApiController(
    private val userService: UserService,
) {
}