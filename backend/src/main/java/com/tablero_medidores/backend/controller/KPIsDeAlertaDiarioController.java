package com.tablero_medidores.backend.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tablero_medidores.backend.DTO.KPIsDTO;
import com.tablero_medidores.backend.model.KPIsDeAlertaDiario;
import com.tablero_medidores.backend.model.Medicion;
import com.tablero_medidores.backend.repository.KPIsDeAlertaDiarioRepository;
import com.tablero_medidores.backend.repository.MedicionRepository;
import com.tablero_medidores.backend.service.KPIService;
import com.tablero_medidores.backend.service.data.Intervalo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/kpis")
@CrossOrigin(origins = "http://localhost:5173")
public class KPIsDeAlertaDiarioController {

    KPIsDeAlertaDiarioRepository repository;

    public KPIsDeAlertaDiarioController(KPIsDeAlertaDiarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<KPIsDeAlertaDiario> getKPIs() {
        return repository.findTopByOrderByFechaDesc()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/ultimo")
    public ResponseEntity<KPIsDeAlertaDiario> getUltimoKPI() {
        return repository.findTopByOrderByFechaDesc()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private Intervalo intervaloUltimoMinuto() {
        LocalDateTime now = LocalDateTime.now();
        return new Intervalo(now.minusMinutes(1), now);
    }

}
