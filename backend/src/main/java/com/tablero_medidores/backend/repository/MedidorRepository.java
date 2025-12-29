package com.tablero_medidores.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tablero_medidores.backend.model.Medidor;
import com.tablero_medidores.backend.model.Operario;

public interface MedidorRepository extends JpaRepository<Medidor, Long> {


}
