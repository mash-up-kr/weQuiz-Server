package kr.mashup.wequiz.controller.ranking

import io.swagger.v3.oas.annotations.media.Schema
import kr.mashup.wequiz.application.ranking.QuizRankingService
import kr.mashup.wequiz.config.auh.UserInfo
import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.domain.answer.QuizAnswer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/ranking")
class RankingApiController(
    private val rankingService: QuizRankingService
) {

    @GetMapping("/quiz/{quizId}")
    fun getQuizAnswerRanking(
        @Schema(hidden = true) @UserInfo userInfoDto: UserInfoDto,
        @PathVariable(name = "quizId") quizId: Long,
        @RequestParam size: Int = 15,
        @RequestParam cursor: Long? = null
    ): GetQuizAnswerRankingResponse {
        val quizAnswers = rankingService.getQuizAnswerRanking(quizId, size, cursor)

        return GetQuizAnswerRankingResponse(
            rankings = quizAnswers.map { it.toRankingPresentation() }
        )
    }
}

data class GetQuizAnswerRankingResponse(
    val rankings: List<QuizAnswerRankingPresentation>
)

data class QuizAnswerRankingPresentation(
    val userInfoDto: UserInfoDto,
    val score: Int
)

fun QuizAnswer.toRankingPresentation() = QuizAnswerRankingPresentation(
    userInfoDto = UserInfoDto.from(user),
    score = totalScore
)
