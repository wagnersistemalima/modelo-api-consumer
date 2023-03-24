package com.sistemalima.spot.adapters.clients.sqs

import br.com.sistemalima.enum.RegrasVeiculoVendasEnum
import com.amazon.sqs.javamessaging.SQSSession
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.sistemalima.spot.adapters.clients.sqs.dto.Request
import com.sistemalima.spot.adapters.clients.sqs.dto.VeiculoRequestDTO
import com.sistemalima.spot.adapters.clients.sqs.mappers.CadastrarVeiculoSQSRequestMapper
import com.sistemalima.spot.adapters.controllers.exception.BusinessException
import com.sistemalima.spot.application.ports.inputs.CadastrarVeiculoSQSUserCase
import com.sistemalima.spot.domain.Observabilidade
import io.micronaut.jms.annotations.JMSListener
import io.micronaut.jms.annotations.Message
import io.micronaut.jms.annotations.Queue
import io.micronaut.jms.sqs.configuration.SqsConfiguration
import io.micronaut.messaging.annotation.MessageBody
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@JMSListener(SqsConfiguration.CONNECTION_FACTORY_BEAN_NAME)
class SqsConsumer(
    private val cadastrarVeiculoSQSUserCase: CadastrarVeiculoSQSUserCase,
    private val cadastrarVeiculoSQSRequestMapper: CadastrarVeiculoSQSRequestMapper
) {

    val logger: Logger = LoggerFactory.getLogger(SqsConsumer::class.java)

    @Queue(value = "\${micronaut.sqs.queue-name}",
        concurrency = "\${micronaut.jms.sqs.concurrency}", acknowledgeMode = SQSSession.UNORDERED_ACKNOWLEDGE)
    fun processarMensagem(
        @Message jmsMessage: javax.jms.Message,
        @MessageBody eventString: String
    ) {

        try {

            logger.info(String.format("$tagStart, $tag, $tagMethod"))

            val request = mapper.readValue(eventString, jacksonTypeRef<Request<VeiculoRequestDTO>>())
            val veiculoRequest = request.data

            val observabilidade = Observabilidade(
                modelo = veiculoRequest.modelo,
                marca = veiculoRequest.marca,
                placa = veiculoRequest.placa
            )
            val veiculo = cadastrarVeiculoSQSRequestMapper.from(veiculoRequest, observabilidade)

            logger.info(String.format("$tagMoviment, preparando o cadastramento do veiculo, $tag, $tagMethod, $observabilidade"))

            cadastrarVeiculoSQSUserCase.execute(veiculo, observabilidade)

            jmsMessage.acknowledge()

            logger.info(String.format("$tagEnd, $tag, $tagMethod, $observabilidade"))

        } catch (exception: BusinessException) {

            logger.error(String.format("Error: $messageVeiculoException, exception: $exception, $tag, $tagMethod"))

            throw BusinessException(messageVeiculoException, RegrasVeiculoVendasEnum.FALHA_DE_NEGOCIO, exception)

        } catch (exception: Exception) {

            logger.error(String.format("Error: $messageException, exception: $exception, $tag, $tagMethod"))

            throw BusinessException("$messageException, exception: $exception", RegrasVeiculoVendasEnum.FALHA_DE_NEGOCIO, exception)
        }
    }

    companion object {
        private val mapper = jacksonObjectMapper()
        private const val tag = "class: SqsConsumer"
        private const val tagMethod = "method: processarMensagem"
        private const val tagStart = "Iniciando o consumo do evento"
        private const val tagMoviment = "Movimentação da request"
        private const val tagEnd = "Finalizando o consumo do evento de cadastro"
        private const val messageException = "Falha generica no consumo da mensagem"
        private const val messageVeiculoException = "Falha de negocio no cadastro do veiculo"
    }
}