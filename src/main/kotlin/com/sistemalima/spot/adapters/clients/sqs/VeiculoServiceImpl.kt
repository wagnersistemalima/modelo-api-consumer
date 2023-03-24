package com.sistemalima.spot.adapters.clients.sqs

import br.com.sistemalima.enum.RegrasVeiculoVendasEnum
import com.sistemalima.spot.adapters.controllers.dto.VeiculoResponse
import com.sistemalima.spot.adapters.controllers.exception.BusinessException
import com.sistemalima.spot.adapters.repositories.configuration.VeiculoRepository
import com.sistemalima.spot.adapters.repositories.mappers.CadastrarVeiculoEntityMapper
import com.sistemalima.spot.application.mappers.CadastrarVeiculoMapper
import com.sistemalima.spot.application.ports.outputs.VeiculoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class VeiculoServiceImpl(
    private val cadastrarVeiculoMapper: CadastrarVeiculoMapper,
    private val veiculoRepository: VeiculoRepository,
    private val cadastrarVeiculoEntityMapper: CadastrarVeiculoEntityMapper
): VeiculoService {

    private val logger: Logger = LoggerFactory.getLogger(VeiculoServiceImpl::class.java)

    override fun findById(id: Long, correlationId: String): VeiculoResponse {
        logger.info(String.format("$tagMoviment, $tag, $tagMethod, $correlationId"))
        try {
            val veiculoEntity = veiculoRepository.findById(id)
            if (veiculoEntity.isEmpty) {
                throw BusinessException("Recurso não encontrado", RegrasVeiculoVendasEnum.NAO_ENCONTRADO)
            }
            return VeiculoResponse(veiculoEntity.get())
        } catch (exception: Exception) {
            throw exception
        }
    }

    companion object {
        private const val tag = "class: VeiculoServiceImpl"
        private const val tagMethod= "findById"
        private const val tagMoviment = "Movimentação da request"
    }
}