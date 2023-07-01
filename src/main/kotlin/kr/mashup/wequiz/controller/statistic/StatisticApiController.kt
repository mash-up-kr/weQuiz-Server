package kr.mashup.wequiz.controller.statistic

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import kr.mashup.wequiz.application.statistic.StatisticService
import kr.mashup.wequiz.config.auh.UserInfo
import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.controller.statistic.model.GetQuizStatisticResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "통계 API", description = "StatisticApiController")
@RestController
@RequestMapping("/api/v1/statistic")
class StatisticApiController(
    private val statisticService: StatisticService,
) {
    @Operation(summary = "퀴즈 통계")
    @GetMapping("/quiz/{quizId}")
    fun getQuizStatistic(
        @Schema(hidden = true) @UserInfo userInfoDto: UserInfoDto,
        @PathVariable(name = "quizId") quizId: Long
    ): GetQuizStatisticResponse {
        return statisticService.getStatistic(quizId)
            .let { GetQuizStatisticResponse.from(it) }
    }
}