package kr.mashup.wequiz.data.exposed.sample

import kr.mashup.wequiz.domain.sample.Sample
import kr.mashup.wequiz.domain.sample.SampleRepository
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component("sampleExposedRepositoryImpl")
class SampleExposedRepositoryImpl : SampleRepository {
    override fun create(sample: Sample): Sample {
        val id = transaction {
            ExposedSampleEntity.insertAndGetId {
                it[name] = sample.name
            }.value
        }

        return Sample(
            id = id,
            name = sample.name
        )
    }
}