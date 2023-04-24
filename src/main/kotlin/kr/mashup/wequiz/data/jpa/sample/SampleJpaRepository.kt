package kr.mashup.wequiz.data.jpa.sample

import org.springframework.data.jpa.repository.JpaRepository

interface SampleJpaRepository: JpaRepository<SampleEntity, Long> {
}