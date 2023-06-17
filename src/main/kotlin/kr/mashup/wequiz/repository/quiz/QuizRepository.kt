package kr.mashup.wequiz.repository.quiz

import kr.mashup.wequiz.domain.quiz.Quiz
import org.springframework.data.jpa.repository.JpaRepository

interface QuizRepository : JpaRepository<Quiz, Long>
