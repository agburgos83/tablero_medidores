package com.tablero_medidores.backend.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tablero_medidores.backend.model.Medidor;
import com.tablero_medidores.backend.repository.MedidorRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/medidores")
public class MedidorController {

    private static final Logger logger = LoggerFactory.getLogger(MedidorController.class);

    private final MedidorRepository medidorRepository;

    public MedidorController(MedidorRepository medidorRepository) {
        this.medidorRepository = medidorRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medidor> find(@PathVariable("id") Long id) {
        return (ResponseEntity<Medidor>) medidorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Medidor>> findAll() {
        List<Medidor> medidores = medidorRepository.findAll();
        return ResponseEntity.ok().body(medidores);
    }

    @PostMapping
    public ResponseEntity<Medidor> create(@RequestBody Medidor medidor) {
        Medidor created = medidorRepository.save(medidor);
        return ResponseEntity.ok(created);
    }

    // @PatchMapping("/{id}")
    // public ResponseEntity<Medidor> update(@PathVariable("id") Long id, Map<String, String> updates) {

    //     Optional<Medidor> searchedMedidor = medidorRepository.findById(id);

    //     if (searchedMedidor.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }

    //     Medidor medidor = searchedMedidor.get();

    //     updates.forEach((field, value) -> {

    //         if (field instanceof String) {
    //             switch (field) {
    //                 case "direccion":
    //                     medidor.setDireccion(value);
    //                     break;
    //             }
    //         } else if (field instanceof Double) {
    //             switch (field) {
    //                 case "latitud":
    //                     medidor.setLatitud(value);
    //                     break;
    //             }
    //         } else if (field instanceof SomeOtherClass) {
    //             // Lógica para SomeOtherClass
    //             SomeOtherClass otherValue = (SomeOtherClass) field;
    //             // ...
    //         }

    //         switch (field) {
    //             case "direccion":
    //                 medidor.setDireccion(value);
    //                 break;
    //             case "barrio":
    //                 medidor.setBarrio(value);
    //                 break;
    //         }
    //     });

    //     Medidor updated = medidorRepository.save(medidor);
    //     return ResponseEntity.ok(updated);

    // }

}
