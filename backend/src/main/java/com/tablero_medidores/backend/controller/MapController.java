package com.tablero_medidores.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.tablero_medidores.backend.geojson.FeatureCollection;
import com.tablero_medidores.backend.model.Medicion;
import com.tablero_medidores.backend.repository.MedicionRepository;
import com.tablero_medidores.backend.service.MapService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/maps")
public class MapController {

    private final MapService service;

    public MapController(MapService service) {
        this.service = service;
    }

    @GetMapping("/laplata")
    public FeatureCollection getMap() throws IOException {
        return service.loadAndEnrich();
    }
}