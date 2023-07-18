// ktlint-disable filename
package kr.mashup.wequiz.lib

import mu.KotlinLogging

open class PrefixLogger(
    private val prefix: String,
    func: () -> Unit
) {
    private val kLogger = KotlinLogging.logger(func)
    fun info(msg: () -> Any?) {
        return kLogger.info {
            "$prefix ${msg()}"
        }
    }

    fun warn(msg: () -> Any?) {
        kLogger.warn {
            "$prefix ${msg()}"
        }
    }

    fun warn(t: Throwable?, msg: () -> Any?) {
        kLogger.warn(t) {
            "$prefix ${msg()}"
        }
    }

    fun error(msg: () -> Any?) {
        kLogger.error {
            "$prefix ${msg()}"
        }
    }

    fun error(t: Throwable?, msg: () -> Any?) {
        kLogger.error(t) {
            "$prefix ${msg()}"
        }
    }
}
