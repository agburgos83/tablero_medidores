package com.tablero_medidores.backend.repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tablero_medidores.backend.model.Paquete;

public interface PaqueteRepository extends JpaRepository<Paquete, Long> {

    List<Paquete> findByTimestamp(LocalDateTime fecha);

    List<Paquete> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

    

}
