package com.sistemalima.spot.adapters.controllers.mappers

import com.sistemalima.spot.adapters.controllers.dto.VeiculoRequest
import com.sistemalima.spot.domain.Observabilidade
import com.sistemalima.spot.domain.Veiculo
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class CadastrarVeiculoRequestMapper {

    private val logger = LoggerFactory.getLogger(CadastrarVeiculoRequestMapper::class.java)

    companion object {
        private val tag = "class: cadastrarVeiculoRequestMapper"
    }

    fun from(veiculoRequest: VeiculoRequest, observabilidade: Observabilidade): Veiculo {
        logger.info("$tag, $observabilidade")
        return Veiculo(
            modelo = veiculoRequest.modelo,
            marca = veiculoRequest.marca,
            placa = veiculoRequest.placa
        )
    }
}