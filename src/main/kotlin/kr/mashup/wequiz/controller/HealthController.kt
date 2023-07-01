package kr.mashup.wequiz.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Health Check", description = "HealthController")
@RestController
class HealthController {
    @GetMapping("/health")
    fun health(): String {

        return "SUCCESS"
    }
}
