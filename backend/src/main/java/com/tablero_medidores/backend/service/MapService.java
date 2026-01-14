package com.tablero_medidores.backend.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tablero_medidores.backend.geojson.Feature;
import com.tablero_medidores.backend.geojson.FeatureCollection;

@Service
public class MapService {

    private static final Logger logger = LoggerFactory.getLogger(MapService.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public FeatureCollection loadAndEnrich() throws IOException {
        // Parsear GeoJSON a FeatureCollection tipada
        FeatureCollection fc = mapper.readValue(
                new ClassPathResource("geo/laplata.geojson").getInputStream(),
                FeatureCollection.class
        );

        // Ejemplo de "enriquecimiento": agregar un código a cada feature si no existe
        List<Feature> features = fc.getFeatures();
        if (features != null) {
            for (Feature f : features) {
                if (!f.getProperties().containsKey("Código")) {
                    // por ejemplo asignamos un ID incremental
                    f.getProperties().put("Código", features.indexOf(f) + 1);
                }
            }
        }

        logger.info("Map loaded with {} features", features.size());
        return fc;
    }
}