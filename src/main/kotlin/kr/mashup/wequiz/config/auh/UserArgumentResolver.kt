package kr.mashup.wequiz.config.auh

import kr.mashup.wequiz.repository.user.UserRepository
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserArgumentResolver(
    private val userRepository: UserRepository
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UserInfo::class.java) &&
            parameter.parameterType == UserInfoDto::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): UserInfoDto {
        val token = webRequest.getHeader("x-wequiz-token") ?: throw RuntimeException() // TODO 403 에러
        val user = userRepository.findByToken(token) ?: throw RuntimeException() // TODO 403 에러

        return UserInfoDto(
            id = user.id,
            name = user.nickname
        )
    }
}
