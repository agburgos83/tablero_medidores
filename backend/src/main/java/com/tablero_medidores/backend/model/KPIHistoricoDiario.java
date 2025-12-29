package com.tablero_medidores.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KPIHistoricoDiario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate fecha;
    private Double consumoPromedio;
    private Double temperatura;

    public KPIHistoricoDiario() {}

    public KPIHistoricoDiario(LocalDate fecha, Double consumoPromedio, Double temperatura) {
        this.fecha = fecha;
        this.consumoPromedio = consumoPromedio;
        this.temperatura = temperatura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Double getConsumoPromedio() {
        return consumoPromedio;
    }

    public Double getTemperatura() {
        return temperatura;
    }

}