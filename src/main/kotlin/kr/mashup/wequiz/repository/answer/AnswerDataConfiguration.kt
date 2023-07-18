package kr.mashup.wequiz.repository.answer

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AnswerDataConfiguration {
    @PersistenceContext
    lateinit var em: EntityManager

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(em)
    }
}
