package com.tablero_medidores.backend.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tablero_medidores.backend.model.Operario;
import com.tablero_medidores.backend.repository.OperarioRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/operarios")
public class OperarioController {

    private static Logger logger = LoggerFactory.getLogger(OperarioController.class);

    private final OperarioRepository operarioRepository;

    public OperarioController(OperarioRepository operarioRepository) {
        this.operarioRepository = operarioRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operario> find(@PathVariable("id") Long id) {
        return (ResponseEntity<Operario>) operarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    public ResponseEntity<List<Operario>> findAll() {
        List<Operario> operarios = operarioRepository.findAll();
        return ResponseEntity.ok().body(operarios);
    }
    
    @PostMapping
    public ResponseEntity<Operario> create(@RequestBody Operario operario) throws Exception {
        Operario created = operarioRepository.save(operario);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Operario> update(@PathVariable Long id, @RequestBody Map<String, String> updates) {

        Optional<Operario> searchedOperario = operarioRepository.findById(id);

        if (searchedOperario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Operario operario = searchedOperario.get();

        updates.forEach((field, value) -> {
            switch (field) {
                case "nombre":
                    operario.setNombre(value);
                    break;
                case "apellido":
                    operario.setApellido(value);
                    break; 
                case "email":
                    operario.setEmail(value);
                    break;
                case "telefono":
                    operario.setTelefono(value);
                    break;
            }
        });


        Operario updated = operarioRepository.save(operario);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Operario> delete(@PathVariable Long id) {
        operarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
