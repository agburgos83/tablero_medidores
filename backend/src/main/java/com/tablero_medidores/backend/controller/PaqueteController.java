package com.tablero_medidores.backend.controller;

import com.tablero_medidores.backend.repository.PaqueteRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tablero_medidores.backend.model.Paquete;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/paquetes")
public class PaqueteController {

    private PaqueteRepository paqueteRepository;

    public PaqueteController(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paquete> findById(@PathVariable("id") Long id) {
        return (ResponseEntity<Paquete>) paqueteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    

}
