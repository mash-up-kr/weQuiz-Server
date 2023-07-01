package kr.mashup.wequiz.repository.answer

import kr.mashup.wequiz.domain.answer.QuestionAnswer
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionAnswerRepository : JpaRepository<QuestionAnswer, Long>
