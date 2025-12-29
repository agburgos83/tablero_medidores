package com.tablero_medidores.backend.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tablero_medidores.backend.model.Medicion;

public interface MedicionRepository extends JpaRepository<Medicion, Long> {

    List<Medicion> findByFecha(LocalDate fecha);
    List<Medicion> findByFechaBetween(LocalDateTime start, LocalDateTime end);

}
