package com.sistemalima.spot.application.mappers

import com.sistemalima.spot.adapters.repositories.entities.VeiculoEntity
import com.sistemalima.spot.domain.Veiculo
import javax.inject.Singleton

@Singleton
class CadastrarVeiculoMapper {

    fun from(veiculo: Veiculo): VeiculoEntity {
        return VeiculoEntity(
            modelo = veiculo.modelo,
            marca = veiculo.marca,
            placa = veiculo.placa
        )
    }
}