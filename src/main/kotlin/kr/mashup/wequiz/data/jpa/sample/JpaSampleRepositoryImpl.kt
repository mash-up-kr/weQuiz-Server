package kr.mashup.wequiz.data.jpa.sample

import kr.mashup.wequiz.domain.sample.Sample
import kr.mashup.wequiz.domain.sample.SampleRepository
import org.springframework.stereotype.Component

@Component("jpaSampleRepositoryImpl")
class JpaSampleRepositoryImpl(
    private val sampleJpaRepository: SampleJpaRepository,
) : SampleRepository {
    override fun create(sample: Sample): Sample {
        return sampleJpaRepository.save(SampleEntity.fromDomain(sample)).toDomain()
    }
}