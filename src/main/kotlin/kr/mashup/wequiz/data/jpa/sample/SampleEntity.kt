package kr.mashup.wequiz.data.jpa.sample

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import kr.mashup.wequiz.domain.sample.Sample

@Entity
@Table(name = "sample")
class SampleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name")
    val name: String = "",
) {
    fun toDomain(): Sample {
        return Sample(
            id = id,
            name = name,
        )
    }

    companion object {
        fun fromDomain(sample: Sample): SampleEntity {
            return SampleEntity(
                id = sample.id,
                name = sample.name,
            )
        }
    }
}