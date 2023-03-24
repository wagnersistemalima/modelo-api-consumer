package com.sistemalima.spot.application.ports.outputs

import com.sistemalima.spot.adapters.controllers.dto.VeiculoResponse

interface VeiculoService {

    fun findById(id: Long, correlationId: String): VeiculoResponse
}