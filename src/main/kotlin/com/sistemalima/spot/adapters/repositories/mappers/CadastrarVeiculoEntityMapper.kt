package com.sistemalima.spot.adapters.repositories.mappers

import com.sistemalima.spot.adapters.controllers.dto.VeiculoResponse
import com.sistemalima.spot.adapters.repositories.entities.VeiculoEntity
import javax.inject.Singleton

@Singleton
class CadastrarVeiculoEntityMapper {

    fun from(veiculoEntity: VeiculoEntity): VeiculoResponse {

        return VeiculoResponse(
            id = veiculoEntity.id,
            modelo = veiculoEntity.modelo,
            marca = veiculoEntity.marca,
            placa = veiculoEntity.placa
        )
    }
}