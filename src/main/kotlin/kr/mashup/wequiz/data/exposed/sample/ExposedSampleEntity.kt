package kr.mashup.wequiz.data.exposed.sample

import kr.mashup.wequiz.domain.sample.Sample
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ResultRow

object ExposedSampleEntity : LongIdTable("sample") {
    val name = varchar("name", 255)
}

fun ExposedSampleEntity.toDomain(row: ResultRow) = Sample(
    id = row[id].value,
    name = row[name],
)