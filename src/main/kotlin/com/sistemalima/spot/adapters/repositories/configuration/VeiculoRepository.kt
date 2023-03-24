package com.sistemalima.spot.adapters.repositories.configuration

import com.sistemalima.spot.adapters.repositories.entities.VeiculoEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface VeiculoRepository: JpaRepository<VeiculoEntity, Long> {
}