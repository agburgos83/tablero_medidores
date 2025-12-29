package com.tablero_medidores.backend.service.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tablero_medidores.backend.model.Medidor;
import com.tablero_medidores.backend.model.Operario;
import com.tablero_medidores.backend.repository.MedidorRepository;
import com.tablero_medidores.backend.repository.OperarioRepository;
import com.tablero_medidores.backend.service.PaqueteService;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MedidorRepository medidorRepository;
    private final OperarioRepository operarioRepository;
    private PaqueteService paqueteService;

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    public DataInitializer(MedidorRepository medidorRepository, OperarioRepository operarioRepository, PaqueteService paqueteService) {
        this.medidorRepository = medidorRepository;
        this.operarioRepository = operarioRepository;
        this.paqueteService = paqueteService;
    }

    @Override
    public void run(String... args) throws Exception {
        
        // creo operarios
        logger.info("DATA INITIALIZER | se crean operarios...");
        Operario operario_0 = new Operario("Juan", "Pérez", "jp@gmail.com", "2255", null);
        Operario operario_1 = new Operario("Marcela", "Alvarado", "ma@gmail.com", "1215", null);
        Operario operario_2 = new Operario("Estaban", "Sosa", "es@gmail.com", "9598", null);

        operarioRepository.save(operario_0);
        operarioRepository.save(operario_1);
        operarioRepository.save(operario_2);

        //creo medidores
        logger.info("DATA INITIALIZER | se crean medidores...");
        Medidor medidor_0 = new Medidor(Barrio.ABASTO, "Av. 520 11410", -34.9650, -58.0800, operario_0);
        Medidor medidor_1 = new Medidor(Barrio.CASCO_URBANO, "4, 1794", -34.9214, -57.9545, operario_0);
        Medidor medidor_2 = new Medidor(Barrio.CITY_BELL, "462, 2847", -34.8600, -58.0300, operario_1);
        Medidor medidor_3 = new Medidor(Barrio.EL_PELIGRO, "Diag. 208, Arturo Seguí", -34.935604,-58.1691295, operario_1);
        Medidor medidor_4 = new Medidor(Barrio.JOAQUIN_GORINA, "Diag. 208, Arturo Seguí", -34.935604,-58.1691295, operario_1);
        Medidor medidor_5 = new Medidor(Barrio.LISANDRO_OLMOS, "C. 50 5200", -34.9400, -58.0300, operario_2);
        Medidor medidor_6 = new Medidor(Barrio.LOS_HORNOS, "65, 1846", -34.9212, -57.9890, operario_0);
        Medidor medidor_7 = new Medidor(Barrio.MANUEL_B_GONNET, "65, 1846", -34.9212, -57.9890, operario_0);
        Medidor medidor_8 = new Medidor(Barrio.MELCHOR_ROMERO, "65, 1846", -34.9212, -57.9890, operario_0);
        Medidor medidor_9 = new Medidor(Barrio.RINGUELET, "512, B1355", -34.8750, -57.9700, operario_0);
        Medidor medidor_10 = new Medidor(Barrio.TOLOSA, "530, 1287", -34.8860, -57.9500, operario_0);
        Medidor medidor_11 = new Medidor(Barrio.VILLA_ELVIRA, "Blvd. 2 2750", -34.9500, -57.9500, operario_1);
        Medidor medidor_12 = new Medidor(Barrio.VILLA_ELISA, "439, entre 135 y 136 (Tranquera, B1894)", -34.8380, -58.0600, operario_1);

        medidorRepository.save(medidor_0);
        medidorRepository.save(medidor_1);
        medidorRepository.save(medidor_2);
        medidorRepository.save(medidor_3);
        medidorRepository.save(medidor_4);
        medidorRepository.save(medidor_5);
        medidorRepository.save(medidor_6);
        medidorRepository.save(medidor_7);
        medidorRepository.save(medidor_8);
        medidorRepository.save(medidor_9);
        medidorRepository.save(medidor_10);
        medidorRepository.save(medidor_11);
        medidorRepository.save(medidor_12);

    }


}
