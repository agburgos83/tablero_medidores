package com.tablero_medidores.backend.model;

import java.util.Date;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KPIsDeConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Long, Double> consumoPromedioPorZona;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Long, Double> consumoTotalPorZona;
    
    public KPIsDeConsumo() {}

    public KPIsDeConsumo(Date fecha, Map<Long, Double> consumoPromedioPorZona,
                         Map<Long, Double> consumoTotalPorZona) {
        this.fecha = fecha;
        this.consumoPromedioPorZona = consumoPromedioPorZona;
        this.consumoTotalPorZona = consumoTotalPorZona;
        
    }
    public Date getFecha() {
        return fecha;
    }

    public Map<Long, Double> getConsumoPromedioPorZona() {
        return consumoPromedioPorZona;
    }

    public Map<Long, Double> getConsumoTotalPorZona() {
        return consumoTotalPorZona;
    }

}
