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

    public DataInitializer(MedidorRepository medidorRepository, OperarioRepository operarioRepository,
            PaqueteService paqueteService) {
        this.medidorRepository = medidorRepository;
        this.operarioRepository = operarioRepository;
        this.paqueteService = paqueteService;
    }

    @Override
    public void run(String... args) throws Exception {

        // creo operarios
        logger.info("DATA INITIALIZER | se crean operarios...");
        Operario operario_0 = new Operario("Igor", "Pérez", "jp@gmail.com", "2255", null);
        Operario operario_1 = new Operario("Marcela", "Alvarado", "ma@gmail.com", "1215", null);
        Operario operario_2 = new Operario("Mario", "Sosa", "es@gmail.com", "9598", null);

        operarioRepository.save(operario_0);
        operarioRepository.save(operario_1);
        operarioRepository.save(operario_2);

        logger.info("DATA INITIALIZER | se crean medidores...");

        // medidores con coordenadas alineadas al geojson (lat, lon)
        Medidor medidor_0 = new Medidor(
                Barrio.ABASTO,
                "Ruta 2 Km 52",
                -35.004917, -58.116155,
                operario_0);

        Medidor medidor_1 = new Medidor(
                Barrio.ALTOS_DE_SAN_LORENZO,
                "Av. 80 y 25",
                -34.967905, -57.923036,
                operario_1);

        Medidor medidor_2 = new Medidor(
                Barrio.ANGEL_ETCHEVERRY,
                "Ruta 36 Km 48",
                -35.106353, -58.033165,
                operario_2);

        Medidor medidor_3 = new Medidor(
                Barrio.ARTURO_SEGUI,
                "Calle 414 y 145",
                -34.901358, -58.126448,
                operario_1);

        Medidor medidor_4 = new Medidor(
                Barrio.LA_PLATA,
                "Calle 12 y 51",
                -34.920524, -57.953588,
                operario_0);

        Medidor medidor_5 = new Medidor(
                Barrio.CITY_BELL,
                "Calle 473 y 14",
                -34.877736, -58.058217,
                operario_1);

        Medidor medidor_6 = new Medidor(
                Barrio.COLONIA_URQUIZA,
                "Calle 185 y 496",
                -34.941694, -58.092636,
                operario_2);

        Medidor medidor_7 = new Medidor(
                Barrio.EDUARDO_ARANA,
                "Calle 137 y 637",
                -34.981884, -57.836744,
                operario_1);

        Medidor medidor_8 = new Medidor(
                Barrio.EL_PELIGRO,
                "Ruta 2 Km 44",
                -34.975670, -58.203449,
                operario_1);

        Medidor medidor_9 = new Medidor(
                Barrio.JOAQUIN_GORINA,
                "Calle 133 y 485",
                -34.905613, -58.047062,
                operario_0);

        Medidor medidor_10 = new Medidor(
                Barrio.JOSE_HERNANDEZ,
                "Calle 25 y 511",
                -34.907612, -58.020077,
                operario_2);

        Medidor medidor_11 = new Medidor(
                Barrio.LISANDRO_OLMOS,
                "Av. 44 y 196",
                -35.030172, -58.009516,
                operario_2);

        Medidor medidor_12 = new Medidor(
                Barrio.LOS_HORNOS,
                "Av. 66 y 143",
                -35.028221, -57.942592,
                operario_0);

        Medidor medidor_13 = new Medidor(
                Barrio.MANUEL_B_GONNET,
                "Calle 502 y 15",
                -34.886741, -58.022993,
                operario_0);

        Medidor medidor_14 = new Medidor(
                Barrio.MELCHOR_ROMERO,
                "Calle 520 y 155",
                -34.947758, -58.047861,
                operario_0);

        Medidor medidor_15 = new Medidor(
                Barrio.RINGUELET,
                "Av. 7 y 514",
                -34.886515, -57.988190,
                operario_0);

        Medidor medidor_16 = new Medidor(
                Barrio.SAN_CARLOS,
                "Av. 44 y 137",
                -34.934365, -58.003129,
                operario_1);

        // sustituí SAVOIA por VILLA_CASTELLS (presente en geojson)
        Medidor medidor_17 = new Medidor(
                Barrio.VILLA_CASTELLS,
                "Calle 487 y 14",
                -34.873667, -58.011342,
                operario_0);

        Medidor medidor_18 = new Medidor(
                Barrio.TOLOSA,
                "Av. 7 y 526",
                -34.899807, -57.980899,
                operario_0);

        Medidor medidor_19 = new Medidor(
                Barrio.VILLA_ELISA,
                "Calle 419 y 25",
                -34.858870, -58.085511,
                operario_1);

        Medidor medidor_20 = new Medidor(
                Barrio.VILLA_ELVIRA,
                "Av. 7 y 80",
                -34.949825, -57.890410,
                operario_1);

        Medidor medidor_21 = new Medidor(
                Barrio.VILLA_GARIBALDI_PARQUE_SICARDI,
                "Calle principal",
                -35.012687, -57.842788,
                operario_2);

        Medidor medidor_22 = new Medidor(
                Barrio.LOS_PORTENOS,
                "Calle principal",
                -34.913504, -58.081948,
                operario_0);

        Medidor medidor_23 = new Medidor(
                Barrio.EL_RINCON,
                "Calle principal",
                -34.886177, -58.094452,
                operario_2);


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
        medidorRepository.save(medidor_13);
        medidorRepository.save(medidor_14);
        medidorRepository.save(medidor_15);
        medidorRepository.save(medidor_16);
        medidorRepository.save(medidor_17);
        medidorRepository.save(medidor_18);
        medidorRepository.save(medidor_19);
        medidorRepository.save(medidor_20);
        medidorRepository.save(medidor_21);
        medidorRepository.save(medidor_22);
        medidorRepository.save(medidor_23);

    }

}