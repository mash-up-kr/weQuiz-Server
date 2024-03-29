package kr.mashup.wequiz.controller.ranking

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import kr.mashup.wequiz.application.answer.QuizAnswerRankingDto
import kr.mashup.wequiz.application.ranking.QuizRankingService
import kr.mashup.wequiz.config.auh.UserInfo
import kr.mashup.wequiz.config.auh.UserInfoDto
import kr.mashup.wequiz.controller.ApiResponse
import kr.mashup.wequiz.repository.answer.MyQuizRankingDto
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
        @RequestParam cursorScore: Int? = null,
        @RequestParam cursorUserId: Long? = null,
        @RequestParam size: Int = 15
    ): ApiResponse<GetMyQuizAnswerRankingResponse> {
        val searchSize = size + 1

        val rankings = rankingService.findMyQuizRanking(
            cursorUserId = cursorUserId,
            cursorScore = cursorScore,
            quizCreatorId = userInfoDto.id,
            size = searchSize
        )

        val last = rankings.getOrNull(size - 1)

        return GetMyQuizAnswerRankingResponse(
            hasNext = rankings.size == searchSize,
            cursorUserId = last?.user?.id,
            cursorScore = last?.totalScore,
            rankings = rankings.take(size).map { it.toRankingPresentation() }
        ).let { ApiResponse.success(it) }
    }

    @Operation(summary = "퀴즈 단건의 랭킹")
    @GetMapping("/quiz/{quizId}")
    fun getQuizAnswerRanking(
        @Schema(hidden = true) @UserInfo userInfoDto: UserInfoDto,
        @PathVariable(name = "quizId") quizId: Long,
        @RequestParam size: Int = 15,
        @RequestParam quizAnswerCursorId: Long? = null
    ): ApiResponse<GetQuizAnswerRankingResponse> {
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
        ).let { ApiResponse.success(it) }
    }
}

data class GetMyQuizAnswerRankingResponse(
    val hasNext: Boolean,
    val cursorUserId: Long? = null,
    val cursorScore: Int? = null,
    val rankings: List<QuizAnswerRankingPresentation>

)

data class GetQuizAnswerRankingResponse(
    val hasNext: Boolean,
    val cursorQuizAnswerId: Long? = null,
    val rankings: List<QuizAnswerRankingPresentation>
)

data class QuizAnswerRankingPresentation(
    val userInfoDto: UserInfoDto,
    val score: Int
)

fun QuizAnswerRankingDto.toRankingPresentation() = QuizAnswerRankingPresentation(
    userInfoDto = UserInfoDto.from(userId, nickname),
    score = totalScore
)

fun MyQuizRankingDto.toRankingPresentation() = QuizAnswerRankingPresentation(
    userInfoDto = UserInfoDto.from(user),
    score = totalScore
)
