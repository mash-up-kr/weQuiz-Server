package kr.mashup.wequiz.controller.ranking

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import kr.mashup.wequiz.application.answer.QuizAnswerRankingDto
import kr.mashup.wequiz.application.ranking.QuizRankingService
import kr.mashup.wequiz.config.auh.UserInfo
import kr.mashup.wequiz.config.auh.UserInfoDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "랭킹 API", description = "RankingApiController")
@RestController
@RequestMapping("/api/v1/ranking")
class RankingApiController(
    private val rankingService: QuizRankingService
) {

    @Operation(summary = "내가 만든 퀴즈들의 총 합 랭킹")
    @GetMapping("/my-quiz")
    fun getMyQuizAnswerRanking(
        @Schema(hidden = true) @UserInfo userInfoDto: UserInfoDto,
        @RequestParam size: Int = 15,
        @RequestParam quizAnswerCursorId: Long? = null
    ): GetQuizAnswerRankingResponse {
        val searchSize = size + 1

        val quizAnswers = rankingService.findMyQuizRanking(
            quizAnswerId = quizAnswerCursorId,
            quizCreatorId = userInfoDto.id,
            size = size
        )

        return GetQuizAnswerRankingResponse(
            hasNext = quizAnswers.size == searchSize,
            cursorQuizAnswerId = quizAnswers.getOrNull(size)?.quizAnswerId,
            rankings = quizAnswers.take(size).map { it.toRankingPresentation() }
        )
    }

    @Operation(summary = "퀴즈 단건 랭킹")
    @GetMapping("/quiz/{quizId}")
    fun getQuizAnswerRanking(
        @Schema(hidden = true) @UserInfo userInfoDto: UserInfoDto,
        @PathVariable(name = "quizId") quizId: Long,
        @RequestParam size: Int = 15,
        @RequestParam quizAnswerCursorId: Long? = null
    ): GetQuizAnswerRankingResponse {
        val searchSize = size + 1
        val quizAnswers = rankingService.findQuizRanking(
            quizAnswerId = quizAnswerCursorId,
            quizId = quizId,
            size = searchSize
        )

        return GetQuizAnswerRankingResponse(
            hasNext = quizAnswers.size == searchSize,
            cursorQuizAnswerId = quizAnswers.getOrNull(size - 1)?.quizAnswerId,
            rankings = quizAnswers.take(size).map { it.toRankingPresentation() }
        )
    }
}

data class GetQuizAnswerRankingResponse(
    val hasNext: Boolean,
    val cursorQuizAnswerId: Long? = null,
    val rankings: List<QuizAnswerRankingPresentation>
)

data class QuizAnswerRankingPresentation(
    val userInfoDto: UserInfoDto,
    val score: Int,
    val quizAnswerId: Long
)

fun QuizAnswerRankingDto.toRankingPresentation() = QuizAnswerRankingPresentation(
    userInfoDto = UserInfoDto.from(userId, nickname),
    score = totalScore,
    quizAnswerId = quizAnswerId
)
