package kr.mashup.wequiz.config.swagger

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.security.SecurityRequirement
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(
    info = Info(title = "WeQuiz API", description = "토큰 발급 후 사용 하시기 바랍니다.")
)
@SecurityScheme(
    type = SecuritySchemeType.APIKEY,
    `in` = SecuritySchemeIn.HEADER,
    name = "x-wequiz-token",
    description = "Auth Token"
)
@Configuration
class SwaggerConfig {
    @Bean
    fun weQuizApiV1(): GroupedOpenApi {
        val paths = arrayOf("/api/v1/**")
        return GroupedOpenApi.builder()
            .group("WeQuiz API v1")
            .addOpenApiCustomizer {
                it.security(
                    listOf(
                        SecurityRequirement().addList(TOKEN_NAME)
                    )
                )
            }
            .pathsToMatch(*paths)
            .build()
    }

    companion object {
        private const val TOKEN_NAME = "x-wequiz-token"
    }
}
