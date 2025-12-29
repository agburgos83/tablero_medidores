package com.tablero_medidores.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

import com.tablero_medidores.backend.model.KPIsDeAlertaDiario;
import com.tablero_medidores.backend.model.Medicion;
import com.tablero_medidores.backend.repository.KPIsDeAlertaDiarioRepository;

@Service
public class KPIService {

    private final KPIsDeAlertaDiarioRepository kpisDeAlertaDiarioRepository;

    public KPIService(KPIsDeAlertaDiarioRepository kpisDeAlertaDiarioRepository) {
        this.kpisDeAlertaDiarioRepository = kpisDeAlertaDiarioRepository;
    }

    // public KPIsDTO calculaKPIs() {

    // LocalDate hoy = LocalDate.now();
    // List<Medicion> medicionesHoy = medicionRepository.findByFecha(hoy);

    // int totalMedidores = (int) medicionesHoy.stream()
    // .map(Medicion::getMedidorId)
    // .distinct()
    // .count();

    // // Simulemos que falla si no reportó en 24 hs
    // LocalDate ayer = hoy.minusDays(1);
    // List<Long> medidoresHoyIds = medicionesHoy.stream()
    // .map(Medicion::getMedidorId)
    // .distinct()
    // .toList();

    // List<Long> medidoresAyerIds = medicionRepository.findByFecha(ayer).stream()
    // .map(Medicion::getMedidorId)
    // .distinct()
    // .toList();

    // int medidoresConFalla = (int) medidoresAyerIds.stream()
    // .filter(id -> !medidoresHoyIds.contains(id))
    // .count();

    // double consumoDiario = medicionesHoy.stream()
    // .mapToDouble(m -> m.getCaudalPromedio() != null ? m.getCaudalPromedio() : 0)
    // .sum();

    // double tempPromedio = medicionesHoy.stream()
    // .mapToDouble(m -> m.getTemperaturaPromedio() != null ?
    // m.getTemperaturaPromedio() : 0)
    // .average()
    // .orElse(0);

    // return new KPIsDTO(totalMedidores, medidoresConFalla, consumoDiario,
    // tempPromedio);

    // }

    public void generarKPIsDeAlertaDiario(List<Medicion> mediciones) {

        LocalDateTime fecha = LocalDateTime.now();

        Integer totalMedidoresTemperaturaAlta = 0;
        Integer totalMedidoresCaudalPromedio = 0;
        Integer totalMedidoresCaudalCero = 0;
        Integer totalMedidoresConSuccion = 0;
        Integer totalMedidoresConPerdida = 0;
        Integer totalMedidoresConAire = 0;
        Integer totalMedidoresFlujoInverso = 0;
        Integer totalMedidoresBateriaBaja = 0;
        Integer totalMedidoresMuestraInvalida = 0;

        for (Medicion med : mediciones) {

            if (med.getTemperaturaPromedio() > 20)
                totalMedidoresTemperaturaAlta++;

            if (med.getCaudalPromedio() > 20)
                totalMedidoresCaudalPromedio++;

            if (med.getSuccion() > 0)
                totalMedidoresConSuccion++;

            if (med.getPerdida() > 0)
                totalMedidoresConPerdida++;

            if (med.getAire() > 0)
                totalMedidoresConAire++;

            if (med.getFlujoInverso() > 0)
                totalMedidoresFlujoInverso++;

            if (med.getCaudalCero() > 0)
                totalMedidoresCaudalCero++;

            if (med.getNivelDeBateria() < 20)
                totalMedidoresBateriaBaja++;

            if (med.getMuestrasInvalidas() > 0)
                totalMedidoresMuestraInvalida++;

        }

        KPIsDeAlertaDiario kpisDeAlertaDiario = new KPIsDeAlertaDiario(fecha, totalMedidoresTemperaturaAlta, 
            totalMedidoresCaudalPromedio, totalMedidoresConSuccion, totalMedidoresConPerdida, 
            totalMedidoresConAire, totalMedidoresFlujoInverso, totalMedidoresCaudalCero, 
            totalMedidoresBateriaBaja, totalMedidoresMuestraInvalida);

        kpisDeAlertaDiarioRepository.save(kpisDeAlertaDiario);


        return; 
    }

}
