package com.sistemalima.spot.adapters.controllers.dto

import com.sistemalima.spot.adapters.repositories.entities.VeiculoEntity

data class VeiculoResponse(
    var id: Long?,
    val modelo: String,
    val marca: String,
    val placa: String
) {
    constructor(veiculoEntity: VeiculoEntity): this (veiculoEntity.id, veiculoEntity.marca, veiculoEntity.modelo, veiculoEntity.placa)
}
