package kr.mashup.wequiz.data.exposed.config

import jakarta.annotation.PostConstruct
import org.jetbrains.exposed.sql.Database
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class ExposedConfig(
    private val dataSource: DataSource,
) {
    @PostConstruct
    fun init() {
        Database.connect(dataSource)
    }
}