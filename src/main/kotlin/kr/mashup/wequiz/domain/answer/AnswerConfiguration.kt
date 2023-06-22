package kr.mashup.wequiz.domain.answer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AnswerConfiguration {
    @Bean
    fun questionAnswerScoreCalculator(): QuestionAnswerScoreCalculator {
        return StandardQuestionAnswerScoreCalculator()
    }
}
