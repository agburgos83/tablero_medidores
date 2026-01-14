package com.tablero_medidores.backend.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tablero_medidores.backend.model.Medicion;

public interface MedicionRepository extends JpaRepository<Medicion, Long> {

    List<Medicion> findByFecha(LocalDate fecha);

    List<Medicion> findByFechaBetween(LocalDateTime start, LocalDateTime end);

    Optional<Medicion> findByMedidorIdAndFecha(Long medidorId, LocalDateTime fecha);

    // Alternativa: native query para devolver directamente los últimos registros completos
    @Query(value = """
        SELECT * FROM medicion m
        WHERE (medidor_id, fecha) IN (
            SELECT medidor_id, MAX(fecha)
            FROM medicion
            GROUP BY medidor_id
        )
    """, nativeQuery = true)
    List<Medicion> findLatestByMedidorNative();

    // Native query para traer la última medición de cada medidor
    @Query(value = """
        SELECT m.*
        FROM medicion m
        INNER JOIN (
            SELECT medidor_id, MAX(fecha) as max_fecha
            FROM medicion
            GROUP BY medidor_id
        ) latest
        ON m.medidor_id = latest.medidor_id AND m.fecha = latest.max_fecha
    """, nativeQuery = true)
    List<Medicion> findLatestByMedidor();

    // Opcional: todas las mediciones de hoy
    @Query("SELECT m FROM Medicion m WHERE m.fecha >= CURRENT_DATE")
    List<Medicion> findToday();

    // Opcional: buscar por ID
    Optional<Medicion> findById(Long id);

}


