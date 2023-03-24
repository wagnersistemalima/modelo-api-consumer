package com.sistemalima.spot.adapters.clients.sqs.configuration

import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import com.amazonaws.services.sqs.AmazonSQS
import com.sistemalima.spot.adapters.clients.sqs.configuration.SqsConfiguration
import io.micronaut.context.event.BeanCreatedEvent
import io.micronaut.context.event.BeanCreatedEventListener
import javax.inject.Singleton
import javax.jms.ConnectionFactory

@Singleton
open class SqsConnectionFactory(
    private val configuration: SqsConfiguration,
    private val sqsClient: AmazonSQS
) : BeanCreatedEventListener<ConnectionFactory?> {
    override fun onCreated(event: BeanCreatedEvent<ConnectionFactory?>): ConnectionFactory? = SQSConnectionFactory(
        ProviderConfiguration().withNumberOfMessagesToPrefetch(configuration.numberOfMessagesToPrefetch),
        sqsClient
    )
}