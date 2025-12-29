package com.tablero_medidores.backend.model;

import java.time.YearMonth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KPIHistoricoMensual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private YearMonth periodo;
    private Double consumoPromedio;
    private Double temperaturaPromedio;

    public KPIHistoricoMensual() {}

    public KPIHistoricoMensual(YearMonth periodo, Double consumoPromedio, Double temperaturaPromedio) {
        this.periodo = periodo;
        this.consumoPromedio = consumoPromedio;
        this.temperaturaPromedio = temperaturaPromedio;
    }

    public YearMonth getPeriodo() {
        return periodo;
    }

    public Double getConsumoPromedio() {
        return consumoPromedio;
    }

    public Double getTemperaturaPromedio() {
        return temperaturaPromedio;
    }

}
