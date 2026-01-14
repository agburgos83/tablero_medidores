package com.tablero_medidores.backend.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KPIsDeAlertaDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    private Integer totalMedidores;
    private Integer totalMedidoresTemperaturaAlta;
    private Integer totalMedidoresCaudalPromedio;
    private Integer totalMedidoresConSuccion;
    private Integer totalMedidoresConPerdida;
    private Integer totalMedidoresConAire;
    private Integer totalMedidoresConFlujoInverso;
    private Integer totalMedidoresConCaudalCero;
    private Integer totalMedidoresConBateriaBaja;
    private Integer totalMedidoresConMuestraInvalida;

    public KPIsDeAlertaDiario() {
    }


    // nivel de bateria + caudal 0 + flujo inverso

    public KPIsDeAlertaDiario(LocalDateTime fecha, Integer totalMedidores, Integer totalMedidoresTemperaturaAlta,
            Integer totalMedidoresCaudalPromedio, Integer totalMedidoresConSuccion, Integer totalMedidoresConPerdida,
            Integer totalMedidoresConAire, Integer totalMedidoresConFlujoInverso, Integer totalMedidoresConCaudalCero,
            Integer totalMedidoresConBateriaBaja, Integer totalMedidoresConMuestraInvalida) {
        this.fecha = fecha;
        this.totalMedidores = totalMedidores;
        this.totalMedidoresTemperaturaAlta = totalMedidoresTemperaturaAlta;
        this.totalMedidoresCaudalPromedio = totalMedidoresCaudalPromedio;
        this.totalMedidoresConSuccion = totalMedidoresConSuccion;
        this.totalMedidoresConPerdida = totalMedidoresConPerdida;
        this.totalMedidoresConAire = totalMedidoresConAire;
        this.totalMedidoresConFlujoInverso = totalMedidoresConFlujoInverso;
        this.totalMedidoresConCaudalCero = totalMedidoresConCaudalCero;
        this.totalMedidoresConBateriaBaja = totalMedidoresConBateriaBaja;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Integer getTotalMedidores() {
        return totalMedidores;
    }

    public Integer getTotalMedidoresTemperaturaAlta() {
        return totalMedidoresTemperaturaAlta;
    }

    public Integer getTotalMedidoresCaudalPromedio() {
        return totalMedidoresCaudalPromedio;
    }

    public Integer getTotalMedidoresConSuccion() {
        return totalMedidoresConSuccion;
    }

    public Integer getTotalMedidoresConPerdida() {
        return totalMedidoresConPerdida;
    }

    public Integer getTotalMedidoresConAire() {
        return totalMedidoresConAire;
    }

    public Integer getTotalMedidoresConFlujoInverso() {
        return totalMedidoresConFlujoInverso;
    }

    public Integer getTotalMedidoresConCaudalCero() {
        return totalMedidoresConCaudalCero;
    }

    public Integer getTotalMedidoresConBateriaBaja() {
        return totalMedidoresConBateriaBaja;
    }

    public Integer getTotalMedidoresConMuestraInvalida() {
        return totalMedidoresConMuestraInvalida;
    }

    public String toString() {
        return "KPIsDeAlertaDiario{" +
                "fecha=" + fecha +
                ", totalMedidoresTemperaturaAlta=" + totalMedidoresTemperaturaAlta +
                ", totalMedidoresCaudalPromedio=" + totalMedidoresCaudalPromedio +
                ", totalMedidoresConSuccion=" + totalMedidoresConSuccion +
                ", totalMedidoresConPerdida=" + totalMedidoresConPerdida +
                ", totalMedidoresConAire=" + totalMedidoresConAire +
                ", totalMedidoresConFlujoInverso=" + totalMedidoresConFlujoInverso +
                ", totalMedidoresConCaudalCero=" + totalMedidoresConCaudalCero +
                ", totalMedidoresConBateriaBaja=" + totalMedidoresConBateriaBaja +
                ", totalMedidoresConMuestraInvalida=" + totalMedidoresConMuestraInvalida +
                '}';
    }
}
