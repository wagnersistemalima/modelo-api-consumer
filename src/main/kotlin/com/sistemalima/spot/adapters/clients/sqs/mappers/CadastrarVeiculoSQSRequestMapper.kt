package com.sistemalima.spot.adapters.clients.sqs.mappers

import com.sistemalima.spot.adapters.clients.sqs.dto.VeiculoRequestDTO
import com.sistemalima.spot.domain.Observabilidade
import com.sistemalima.spot.domain.Veiculo
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class CadastrarVeiculoSQSRequestMapper {

    private val logger = LoggerFactory.getLogger(CadastrarVeiculoSQSRequestMapper::class.java)

    companion object {
        private val tag = "class: CadastrarVeiculoSQSRequestMapper"
    }

    fun from(veiculoRequest: VeiculoRequestDTO, observabilidade: Observabilidade): Veiculo {
        logger.info("$tag, $observabilidade")
        return Veiculo(
            modelo = veiculoRequest.modelo,
            marca = veiculoRequest.marca,
            placa = veiculoRequest.placa
        )
    }
}