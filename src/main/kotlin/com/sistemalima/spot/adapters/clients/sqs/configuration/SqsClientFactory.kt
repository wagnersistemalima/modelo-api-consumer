package com.sistemalima.spot.adapters.clients.sqs.configuration

import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class SqsClientFactory {

    @Bean
    @Singleton
    fun defaultSqsClientLocal(): AmazonSQS {
        return AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1).build()
    }

}