package kr.mashup.wequiz.domain.statistic

import kr.mashup.wequiz.domain.quiz.option.Option

class OptionStatistic(
    private val option: Option,
    private val optionSelectCount: Int,
    private val questionTotalAnswerCount: Int,
) {
    val optionId: Long = option.id

    val content: String = option.content

    val selectivity: Float = (optionSelectCount / questionTotalAnswerCount).toFloat()
}