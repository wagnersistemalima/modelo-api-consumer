package com.sistemalima.spot.application.usercase

import com.sistemalima.spot.adapters.controllers.dto.VeiculoResponse
import com.sistemalima.spot.adapters.repositories.configuration.VeiculoRepository
import com.sistemalima.spot.adapters.repositories.mappers.CadastrarVeiculoEntityMapper
import com.sistemalima.spot.application.mappers.CadastrarVeiculoMapper
import com.sistemalima.spot.application.ports.inputs.CadastrarVeiculoUserCase
import com.sistemalima.spot.domain.Observabilidade
import com.sistemalima.spot.domain.Veiculo
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class CadastrarVeiculoUserCaseImpl(
    private val cadastrarVeiculoMapper: CadastrarVeiculoMapper,
    private val veiculoRepository: VeiculoRepository,
    private val cadastrarVeiculoEntityMapper: CadastrarVeiculoEntityMapper
): CadastrarVeiculoUserCase {

    private val logger = LoggerFactory.getLogger(CadastrarVeiculoUserCaseImpl::class.java)

    companion object {
        private val tag = "class: CadastrarVeiculoUserCaseImpl"
        private const val tagMethod = "method: execute"
        private const val tagMoviment = "Movimentação da request"
    }

    override fun execute(veiculo: Veiculo, observabilidade: Observabilidade): VeiculoResponse {

        logger.info(String.format("$tagMoviment, $tag, $tagMethod, $observabilidade"))

        val veiculoEntity = cadastrarVeiculoMapper.from(veiculo)

        val veiculoResponse = veiculoRepository.save(veiculoEntity)

        return cadastrarVeiculoEntityMapper.from(veiculoResponse)
    }
}