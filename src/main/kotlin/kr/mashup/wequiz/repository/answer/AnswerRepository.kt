package kr.mashup.wequiz.repository.answer

import kr.mashup.wequiz.domain.answer.Answer
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<Answer, Long>
