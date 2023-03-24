package com.sistemalima.spot.adapters.controllers.dto

import io.micronaut.core.annotation.Introspected
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@Introspected
data class VeiculoRequest(
    @field:Valid
    @field:NotBlank(message = "Informe o modelo do veiculo")
    val modelo: String,

    @field:Valid
    @field:NotBlank(message = "Informe a marca do veiculo")
    val marca: String,

    @field:Valid
    @field:NotBlank(message = "Informe a placa do veiculo")
    val placa: String
)