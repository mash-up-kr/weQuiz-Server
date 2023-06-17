package kr.mashup.wequiz.domain.quiz

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuizConfiguration {
    @Bean
    fun questionScoreCalculator(): QuestionScoreCalculator {
        return SimpleQuestionScoreCalculator()
    }
}
