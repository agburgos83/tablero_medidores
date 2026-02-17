package com.tablero_medidores.backend.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tablero_medidores.backend.model.KPIsDeAlertaDiario;
import com.tablero_medidores.backend.model.Medicion;
import com.tablero_medidores.backend.repository.MedicionRepository;
// import com.tablero_medidores.backend.service.MedicionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mediciones")
// @CrossOrigin(origins = "http://localhost:5173")
public class MedicionController {

    private final MedicionRepository medicionRepository;

    public MedicionController(MedicionRepository medicionRepository) {
        this.medicionRepository = medicionRepository;
    }

    // Última medición de cada medidor
    @GetMapping("/latest")
    public List<Medicion> latestPerMedidor() {
        return medicionRepository.findLatestByMedidor();
    }

    // Todas las mediciones de hoy
    // @GetMapping("/today")
    // public List<Medicion> today() {
    //     return medicionRepository.findToday();
    // }

    // Buscar por ID
    @GetMapping("/{id}")
    public Medicion findById(@PathVariable Long id) {
        return medicionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medición no encontrada"));
    }

    // Crear una medición
    @PostMapping
    public Medicion create(@RequestBody Medicion medicion) {
        return medicionRepository.save(medicion);
    }
}