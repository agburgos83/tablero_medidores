package com.tablero_medidores.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tablero_medidores.backend.model.Medicion;
import com.tablero_medidores.backend.repository.MedicionRepository;
// import com.tablero_medidores.backend.service.MedicionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mediciones")
@RequiredArgsConstructor
public class MedicionController {

    private MedicionRepository medicionRepository;

    public MedicionController(MedicionRepository medicionRepository) {
        this.medicionRepository = medicionRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicion> findById(@PathVariable("id") Long id) {
        return (ResponseEntity<Medicion>) medicionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Medicion> findAll() {
        return medicionRepository.findAll();
    }

    @PostMapping
    public Medicion createMedicion(@RequestBody Medicion medicion) {
        return medicionRepository.save(medicion);
    }

}