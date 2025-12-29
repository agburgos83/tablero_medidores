package com.tablero_medidores.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tablero_medidores.backend.model.Medicion;
import com.tablero_medidores.backend.model.Paquete;
import com.tablero_medidores.backend.repository.MedicionRepository;
import com.tablero_medidores.backend.service.data.DataPaquete;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class MedicionService {

    private final MedicionRepository medidorRepository;

    private static Logger logger = LoggerFactory.getLogger(MedicionService.class);

    private final ObjectMapper mapper;

    public MedicionService(MedicionRepository medidorRepository, ObjectMapper mapper) {
        this.medidorRepository = medidorRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void generarMediciones(List<Paquete> paquetes) throws JsonMappingException, JsonProcessingException {
        for (Paquete p : paquetes) {
            generarMedicion(p);
        }
    }

    private void generarMedicion(Paquete paquete) throws JsonMappingException, JsonProcessingException {

        String data = paquete.getData();

        byte[] decodedBytes = Base64.getDecoder().decode(data);

        String decodedData = new String(decodedBytes);

        // 2. Parsear el JSON a DataPaquete
        DataPaquete dataPaquete = mapper.readValue(decodedData, DataPaquete.class);

        Medicion medicion = new Medicion(
                paquete.getMedidor().getId(),
                paquete.getFecha(),
                paquete.getMedidor().getDireccion(),
                paquete.getMedidor().getLatitud(),
                paquete.getMedidor().getLongitud(),
                dataPaquete.getInicioPaquete(),
                dataPaquete.getFinPaquete(),
                dataPaquete.getTimestamp(),
                dataPaquete.getDireccionLora(),
                dataPaquete.getTemperaturaPromedio(),
                dataPaquete.getNivelDeBateria(),
                dataPaquete.getAire(),
                dataPaquete.getSuccion(),
                dataPaquete.getPerdida(),
                dataPaquete.getFlujoInverso(),
                dataPaquete.getCaudalCero(),
                dataPaquete.getMuestrasInvalidas(),
                dataPaquete.getCaudalPromedio(),
                dataPaquete.getMedicionesDeVolumen());

        medidorRepository.save(medicion);

    }

}
