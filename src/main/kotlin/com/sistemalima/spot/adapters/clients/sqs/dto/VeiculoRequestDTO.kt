package com.sistemalima.spot.adapters.clients.sqs.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class VeiculoRequestDTO(

    @JsonProperty("modelo")
    val modelo: String,

    @JsonProperty("marca")
    val marca: String,

    @JsonProperty("placa")
    val placa: String
)
