package kr.mashup.wequiz.controller.user

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import kr.mashup.wequiz.application.user.UserService
import kr.mashup.wequiz.config.auh.UserInfo
import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.controller.ApiResponse
import kr.mashup.wequiz.controller.user.model.JoinUserRequest
import org.springframework.web.bind.annotation.GetMapping
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
    ): ApiResponse<Boolean> {
        userService.join(
            token = joinUserRequest.token,
            phone = joinUserRequest.phone,
            nickname = joinUserRequest.nickname,
            description = joinUserRequest.description
        )
        return ApiResponse.success(true)
    }

    @Operation(summary = "내 정보 조회하기")
    @GetMapping()
    fun me(
        @Schema(hidden = true) @UserInfo userInfoDto: UserInfoDto
    ): ApiResponse<UserPresentation> {
        val user = userService.find(userInfoDto.id)

        return UserPresentation(
            id = user.id,
            nickname = user.nickname,
            description = user.description
        ).let { ApiResponse.success(it) }
    }

    data class UserPresentation(
        val id: Long,
        val nickname: String,
        val description: String
    )
}
