package kr.mashup.wequiz.application.sample

import kr.mashup.wequiz.domain.sample.Sample
import kr.mashup.wequiz.domain.sample.SampleRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class SampleJpaService(
    @Qualifier("jpaSampleRepositoryImpl")
    private val sampleRepository: SampleRepository,
) {
    fun createSample(): Sample {
        return sampleRepository.create(Sample())
    }
}