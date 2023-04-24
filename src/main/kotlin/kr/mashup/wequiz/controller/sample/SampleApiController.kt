package kr.mashup.wequiz.controller.sample

import kr.mashup.wequiz.application.sample.SampleExposedService
import kr.mashup.wequiz.application.sample.SampleJpaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleApiController(
    private val sampleJpaService: SampleJpaService,
    private val sampleExposedService: SampleExposedService,
) {
    @GetMapping("/jpa-sample")
    fun jpaSample(): String {
        return sampleJpaService.createSample().id.toString()
    }

    @GetMapping("/exposed-sample")
    fun exposedSample(): String {
        return sampleExposedService.createSample().id.toString()
    }
}