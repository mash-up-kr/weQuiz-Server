package kr.mashup.wequiz.domain.sample

interface SampleRepository {
    fun create(sample: Sample): Sample
}