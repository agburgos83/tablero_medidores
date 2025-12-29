package com.tablero_medidores.backend.service;

// import static org.mockito.Answers.values;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tablero_medidores.backend.DTO.PaqueteDTO;
import com.tablero_medidores.backend.model.Medidor;
import com.tablero_medidores.backend.model.Paquete;
import com.tablero_medidores.backend.repository.PaqueteRepository;
import com.tablero_medidores.backend.service.data.DataPaquete;

import jakarta.transaction.Transactional;

@Service
public class PaqueteService {

    private PaqueteRepository paqueteRepository;
    List<Map<String, Object>> paquetes;
    private final ObjectMapper mapper;

    public PaqueteService(PaqueteRepository paqueteRepository, ObjectMapper mapper) {
        this.paqueteRepository = paqueteRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void generarPaquetes(List<Medidor> medidores) throws JsonProcessingException {

        for (Medidor medidor : medidores) {

            // 1 generar paqueteDTO
            Paquete paquete = generarPaquete(medidor);

            paqueteRepository.save(paquete);
        }
    }

    private Paquete generarPaquete(Medidor medidor) throws JsonProcessingException {

        List<Double> valoresVolumen = generarListaDeVolumen();

        DataPaquete dataPaquete = new DataPaquete(
                ThreadLocalRandom.current().nextInt(16, 68),
                ThreadLocalRandom.current().nextInt(16, 68),
                LocalDateTime.now(),
                ThreadLocalRandom.current().nextInt(0, 1000),
                ThreadLocalRandom.current().nextDouble(10, 40),
                ThreadLocalRandom.current().nextDouble(0, 100),
                ThreadLocalRandom.current().nextInt(0, 10),
                ThreadLocalRandom.current().nextInt(0, 10),
                ThreadLocalRandom.current().nextInt(0, 10),
                ThreadLocalRandom.current().nextInt(0, 10),
                ThreadLocalRandom.current().nextInt(0, 10),
                ThreadLocalRandom.current().nextInt(0, 10),
                ThreadLocalRandom.current().nextDouble(0, 5),
                valoresVolumen);

        // codificas a base 64
        String json = mapper.writeValueAsString(dataPaquete);
        String data_Base64 = Base64.getEncoder().encodeToString(json.getBytes());

        Paquete paquete = new Paquete(data_Base64, medidor, LocalDateTime.now());

        return paquete;
    }

    // private List<Double> generarListaDeVolumen() {
    // DecimalFormat df = new DecimalFormat("0.00");

    // return ThreadLocalRandom.current()
    // .doubles(24, 100, 5000) // 24 valores entre 100 y 5000
    // .mapToObj(d -> Double.valueOf(df.format(d)))
    // .collect(Collectors.toList());
    // }

    private List<Double> generarListaDeVolumen() {
        return ThreadLocalRandom.current()
                .doubles(24, 100, 5000) // 24 valores entre 100 y 5000
                .map(d -> Math.round(d * 100.0) / 100.0) // redondea a 2 decimales
                .boxed()
                .collect(Collectors.toList());
    }

}
