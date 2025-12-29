package com.tablero_medidores.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.tablero_medidores.backend.service.MedicionService;

@SpringBootApplication
@EnableScheduling
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

/*     @Bean
    CommandLineRunner init(MedicionService medicionService) {
        return args -> {
            String path = "../docs/datos_medidores.txt"; // ruta relativa desde /backend
            medicionService.cargarDesdeArchivo(path);
        };
    }
 */
}
