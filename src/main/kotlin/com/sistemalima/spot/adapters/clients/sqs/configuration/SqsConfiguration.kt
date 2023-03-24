package com.sistemalima.spot.adapters.clients.sqs.configuration

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Replaces
import io.micronaut.context.annotation.Requires
import io.micronaut.context.annotation.Value
import io.micronaut.jms.sqs.configuration.properties.SqsConfigurationProperties


@Replaces(SqsConfigurationProperties::class)
@ConfigurationProperties(SqsConfigurationProperties.PREFIX)  // micronaut.jms.sqs
@Requires(property = SqsConfigurationProperties.PREFIX + ".enabled", value = "true")
class SqsConfiguration(
    @Value("\${micronaut.jms.sqs.enabled}")
    var enabled: Boolean = false,

    @Value("\${micronaut.jms.sqs.message-to-prefetch}")
    var messagesToPrefetch: Int = 0,

    @Value("\${micronaut.jms.sqs.concurrency}")
    var concurrency: String = "1-1"
) : SqsConfigurationProperties {

    override fun isEnabled(): Boolean {
        return enabled
    }

    override fun getNumberOfMessagesToPrefetch(): Int {
        return messagesToPrefetch
    }
}