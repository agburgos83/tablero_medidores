package com.tablero_medidores.backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tablero_medidores.backend.model.Medidor;
import com.tablero_medidores.backend.repository.MedicionRepository;
import com.tablero_medidores.backend.service.data.Intervalo;
import com.tablero_medidores.backend.service.mail.EmailDetails;
import com.tablero_medidores.backend.service.mail.EmailService;

@Service
public class AlertaService {

    private final MedicionRepository medicionRepository;
    private final EmailService mailService;
    // private String recipient = "agburgos.brc@gmail.com";

    private static final Logger logger = LoggerFactory.getLogger(AlertaService.class);

    public AlertaService(EmailService mailService, MedicionRepository medicionRepository) {
        this.medicionRepository = medicionRepository;
        this.mailService = mailService;
    }

    public void chequearAlertas() {
        LocalDateTime oldest = medicionRepository.findOldestMeasurement();

        if (oldest == null) {
            logger.info("Alertas no activas: no hay mediciones");
            return;
        }

        if (oldest.isAfter(LocalDateTime.now().minus(30, ChronoUnit.SECONDS))) {
            logger.info("Alertas no activas: aún no hay ventana de 3 períodos");
            return;
        }

        var rango = intervaloUltimoMinuto();
        LocalDateTime from = rango.start();
        LocalDateTime to = rango.end();

        List<Medidor> medidoresEnAlerta = medicionRepository.findAllHighTempSince(from, to);

        if (!medidoresEnAlerta.isEmpty()) {
            var details = createEmailDetails(medidoresEnAlerta);
            mailService.sendSimpleMail(details);
        }

    }

    private EmailDetails createEmailDetails(List<Medidor> medidoresEnAlerta) {

        String inicio = "Los medidores con temperatura alta que tenés que revisar son: \n \n";
        String cuerpo = medidoresEnAlerta.stream()
                .map(m -> m.toString())
                .collect(Collectors.joining("\n"));

        return new EmailDetails("noreply@tablero.com", "agburgos.brc@gmail.com",
                "Alerta temperatura " + LocalDate.now() + "", inicio + cuerpo);
    }

    private Intervalo intervaloUltimoMinuto() {
        LocalDateTime now = LocalDateTime.now();
        return new Intervalo(now.minusMinutes(1), now);
    }

}
