package com.tablero_medidores.backend.service.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tablero_medidores.backend.model.Medicion;
import com.tablero_medidores.backend.model.Medidor;
import com.tablero_medidores.backend.model.Paquete;
import com.tablero_medidores.backend.repository.MedicionRepository;
import com.tablero_medidores.backend.repository.MedidorRepository;
import com.tablero_medidores.backend.repository.PaqueteRepository;
import com.tablero_medidores.backend.service.KPIService;
import com.tablero_medidores.backend.service.MedicionService;
import com.tablero_medidores.backend.service.PaqueteService;

@Component
public class DataScheduler {

    private final PaqueteService paqueteService;
    private final MedicionService medicionService;
    private final KPIService kpiService;
    private final MedidorRepository medidorRepository;
    private final PaqueteRepository paqueteRepository;
    private final MedicionRepository medicionRepository;

    private static final Logger logger = LoggerFactory.getLogger(DataScheduler.class);

    public DataScheduler(MedidorRepository medidorRepository, PaqueteRepository paqueteRepository,
            MedicionRepository medicionRepository, PaqueteService paqueteService,
            MedicionService medicionService, KPIService kpiService) {
        this.medidorRepository = medidorRepository;
        this.paqueteRepository = paqueteRepository;
        this.medicionRepository = medicionRepository;
        this.paqueteService = paqueteService;
        this.medicionService = medicionService;
        this.kpiService = kpiService;
    }

    @Scheduled(initialDelay = 30000, fixedRate = 300000)
    public void generarDatos() throws JsonProcessingException {

        // se recuperan medidores y persisten paquetes
        logger.info("DATA SCHEDULER | Se crean paquetes...");
        List<Medidor> medidores = medidorRepository.findAll();
        paqueteService.generarPaquetes(medidores);

        // se recuperan paquetes y persisten mediciones
        logger.info("DATA SCHEDULER | Se crean mediciones...");
        var rangoPaquetes = intervaloUltimoMinuto();
        List<Paquete> paquetes = paqueteRepository.findByFechaBetween(rangoPaquetes.start(), rangoPaquetes.end());
        medicionService.generarMediciones(paquetes);

        // se recuperan paquetes y persisten mediciones
        logger.info("DATA SCHEDULER | Se crean Indicadores de alerta...");
        var rangoMediciones = intervaloUltimoMinuto();
        List<Medicion> mediciones = medicionRepository.findByFechaBetween(rangoMediciones.start(),
                rangoMediciones.end());
        kpiService.generarKPIsDeAlertaDiario(mediciones);
    }

    private Intervalo intervaloUltimoMinuto() {
        LocalDateTime now = LocalDateTime.now();
        return new Intervalo(now.minusMinutes(1), now);
    }

}
