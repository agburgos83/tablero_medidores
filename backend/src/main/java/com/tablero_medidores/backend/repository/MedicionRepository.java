package com.tablero_medidores.backend.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tablero_medidores.backend.model.Medicion;
import com.tablero_medidores.backend.model.Medidor;

public interface MedicionRepository extends JpaRepository<Medicion, Long> {

    List<Medicion> findByTimestamp(LocalDate fecha);

    List<Medicion> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

    Optional<Medicion> findByMedidorIdAndTimestamp(Long medidorId, LocalDateTime fecha);

    // Alternativa: native query para devolver directamente los últimos registros
    // completos
    @Query(value = """
                SELECT * FROM medicion m
                WHERE (medidor_id, timestamp) IN (
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
                ON m.medidor_id = latest.medidor_id AND m.timestamp = latest.max_fecha
            """, nativeQuery = true)
    List<Medicion> findLatestByMedidor();

    // Opcional: todas las mediciones de hoy
    // @Query("SELECT m FROM Medicion m WHERE m.fecha >= CURRENT_DATE")
    // List<Medicion> findToday();

    // Opcional: buscar por ID
    Optional<Medicion> findById(Long id);

    // @Query(value = """
    // SELECT DISTINCT m.*
    // FROM medidor m
    // JOIN medicion me ON me.medidor_id = m.id
    // WHERE me.temperatura_promedio > 30
    // AND me.timestamp >= now() - interval '3 minutes'
    // """, nativeQuery = true)
    // List<Medidor> findAllHighTemp();

    @Query("""
                SELECT DISTINCT p.medidor
                FROM Medicion me
                JOIN me.paquete p
                WHERE me.temperaturaPromedio > 35
                  AND me.timestamp BETWEEN :from AND :to
            """)
    List<Medidor> findAllHighTempSince(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query(value = """
                SELECT min(me.timestamp)
                FROM medicion me
            """, nativeQuery = true)
    LocalDateTime findOldestMeasurement();

    // @Query(value = """
    // SELECT DISTINCT p.medidor
    // FROM Medicion me
    // JOIN me.paquete p
    // WHERE me.temperaturaPromedio > 30
    // AND me.timestamp >= CURRENT_TIMESTAMP - 3/1440.0
    // """)
    // List<Medidor> findAllHighTempLast3Minutes();

}
