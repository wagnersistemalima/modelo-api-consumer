package com.sistemalima.spot.application.ports.inputs

import com.sistemalima.spot.adapters.controllers.dto.VeiculoResponse
import com.sistemalima.spot.domain.Observabilidade
import com.sistemalima.spot.domain.Veiculo


interface CadastrarVeiculoSQSUserCase {
    fun execute(veiculo: Veiculo, observabilidade: Observabilidade): VeiculoResponse
}