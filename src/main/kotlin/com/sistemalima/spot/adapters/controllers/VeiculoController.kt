package com.sistemalima.spot.adapters.controllers

import com.sistemalima.spot.adapters.controllers.dto.VeiculoRequest
import com.sistemalima.spot.adapters.controllers.dto.VeiculoResponse
import com.sistemalima.spot.adapters.controllers.mappers.CadastrarVeiculoRequestMapper
import com.sistemalima.spot.application.ports.inputs.CadastrarVeiculoUserCase
import com.sistemalima.spot.application.ports.outputs.VeiculoService
import com.sistemalima.spot.domain.Observabilidade
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import java.util.*
import javax.validation.Valid

@Controller(value = "/veiculos", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
@Validated
class VeiculoController(
    private val cadastrarVeiculoRequestMapper: CadastrarVeiculoRequestMapper,
    private val cadastrarVeiculoUserCase: CadastrarVeiculoUserCase,
    private val veiculoService: VeiculoService
) {

    private val logger = LoggerFactory.getLogger(VeiculoController::class.java)

    companion object {
        private val tag = "class: VeiculoController"
        private val tagCreate = "method: create [POST]"
        private val tagFindById = "method: findById [GET]"
        private val tagStartMessage = "Inicio do processo request"
        private val tagEndMessage = "Fim do processo request"
    }

    @Post
    fun create(@Body @Valid veiculoRequest: VeiculoRequest) : HttpResponse<VeiculoResponse> {
        val observabilidade = Observabilidade(veiculoRequest.modelo, veiculoRequest.marca, veiculoRequest.placa)
        logger.info(String.format("$tag, $tagCreate, $tagStartMessage: $observabilidade"))
        val veiculo = cadastrarVeiculoRequestMapper.from(veiculoRequest, observabilidade)
        val veiculoResponse = cadastrarVeiculoUserCase.execute(veiculo, observabilidade)

        logger.info(String.format("$tag, $tagCreate, $tagEndMessage: $observabilidade"))
        return HttpResponse.created(veiculoResponse)
    }

    @Get(value = "/{id}")
    fun findById(@PathVariable id: Long): HttpResponse<VeiculoResponse> {
        val correlationId = UUID.randomUUID().toString()
        logger.info(String.format("$tag, $tagFindById, $tagStartMessage, id: $id, correlationId= $correlationId"))
        val veiculoResaponse = veiculoService.findById(id, correlationId)
        logger.info(String.format("$tag, $tagFindById, $tagEndMessage, id: $id, correlationId= $correlationId"))
        return HttpResponse.ok(veiculoResaponse)
    }
}