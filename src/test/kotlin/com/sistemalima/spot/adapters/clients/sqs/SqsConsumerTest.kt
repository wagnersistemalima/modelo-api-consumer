package com.sistemalima.spot.adapters.clients.sqs

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.sistemalima.spot.adapters.clients.sqs.configuration.SqsConnectionFactory
import io.micronaut.context.annotation.Replaces
import io.micronaut.context.event.BeanCreatedEvent
import io.micronaut.context.event.BeanCreatedEventListener
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject
import javax.jms.ConnectionFactory
import javax.jms.Message

@MicronautTest(startApplication = false)
internal class SqsConsumerTest {

    @Inject
    private val sqsConsumer: SqsConsumer = SqsConsumer()

    companion object {
        private const val PORT = 8081
        private val wireMockServer = WireMockServer(PORT)

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            wireMockServer.start()
            WireMock.configureFor("localhost", PORT)
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            wireMockServer.start()
        }
    }

    @BeforeEach
    fun reset() {
        WireMock.reset()
    }

    @Replaces(SqsConnectionFactory::class)
    @MockBean(bean = SqsConnectionFactory::class)
    internal class SqsConnectionFactoryForTests: BeanCreatedEventListener<ConnectionFactory?> {
        override fun onCreated(event: BeanCreatedEvent<ConnectionFactory?>?): ConnectionFactory? = mockk(relaxed = true)
    }

    @Test
    fun `testar novo consumer`() {

        val message: Message = mockk {
            every { acknowledge() } returns Unit
            every { jmsMessageID } returns "message-id"
        }

        val body = "body request message"
        sqsConsumer.processarMensagem(message, body)

        // Então
        verify (exactly = 1 )  {message.acknowledge()}
    }
}