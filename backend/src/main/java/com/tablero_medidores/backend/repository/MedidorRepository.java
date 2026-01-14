package com.tablero_medidores.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tablero_medidores.backend.model.Medidor;

public interface MedidorRepository extends JpaRepository<Medidor, Long> {


}
