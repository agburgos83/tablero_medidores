package com.tablero_medidores.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tablero_medidores.backend.model.KPIsDeAlertaDiario;

public interface KPIsDeAlertaDiarioRepository extends JpaRepository<KPIsDeAlertaDiario, Long> {

    List<KPIsDeAlertaDiario> findByFechaBetween(LocalDateTime start, LocalDateTime end);

    Optional<KPIsDeAlertaDiario> findTopByOrderByFechaDesc();

}
