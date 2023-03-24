package com.sistemalima.spot.adapters.repositories.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "veiculo")
data class VeiculoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    val modelo: String,
    val marca: String,
    val placa: String
)
