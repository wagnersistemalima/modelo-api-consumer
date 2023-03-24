package com.sistemalima.spot.application.usercase

import com.sistemalima.spot.adapters.controllers.dto.VeiculoResponse
import com.sistemalima.spot.adapters.repositories.configuration.VeiculoRepository
import com.sistemalima.spot.adapters.repositories.mappers.CadastrarVeiculoEntityMapper
import com.sistemalima.spot.application.mappers.CadastrarVeiculoMapper
import com.sistemalima.spot.application.ports.inputs.CadastrarVeiculoSQSUserCase
import com.sistemalima.spot.domain.Observabilidade
import com.sistemalima.spot.domain.Veiculo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class CadastrarVeiculoSQSUserCaseImpl(
    private val cadastrarVeiculoMapper: CadastrarVeiculoMapper,
    private val veiculoRepository: VeiculoRepository,
    private val cadastrarVeiculoEntityMapper: CadastrarVeiculoEntityMapper
): CadastrarVeiculoSQSUserCase {

    private val logger: Logger = LoggerFactory.getLogger(CadastrarVeiculoSQSUserCaseImpl::class.java)

    companion object {
        private val tag = "class: CadastrarVeiculoSQSUserCaseImpl"
    }

    override fun execute(veiculo: Veiculo, observabilidade: Observabilidade): VeiculoResponse {
        logger.info("$tag, $observabilidade")
        val veiculoEntity = cadastrarVeiculoMapper.from(veiculo)
        val veiculoResponse = veiculoRepository.save(veiculoEntity)
        return cadastrarVeiculoEntityMapper.from(veiculoResponse)
    }
}