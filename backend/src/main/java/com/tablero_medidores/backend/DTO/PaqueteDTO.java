package com.tablero_medidores.backend.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class PaqueteDTO {

    // datos asociados
    private Long medidorId;
    private Long paqueteId;
    private LocalDateTime fecha;
    private String direccion;
    private Double latitud;
    private Double longitud;
    
    // datos propios del paquete
    private Integer inicioPaquete;
    private Integer finPaquete;
    private LocalDateTime timestamp;
    private Integer direccionLora;
    private Double temperaturaPromedio;
    private Double nivelDeBateria;

    // contadores
    private Integer succion;
    private Integer aire;
    private Integer perdida;
    private Integer flujoInverso;
    private Integer caudalCero;
    private Integer muestrasInvalidas;

    private Double caudalPromedio;
    private List<Double> medicionesDeVolumen;

    public Long getMedidorId() {
        return medidorId != null ? medidorId : null;
    }

    public Long getPaqueteId() {
        return paqueteId != null ? paqueteId : null;
    }

    public LocalDateTime getFecha() {
        return fecha != null ? fecha : null;
    }

    public Integer getInicioPaquete() {
        return inicioPaquete != null ? inicioPaquete : null;
    }

    public Integer getFinPaquete() {
        return finPaquete != null ? finPaquete : null;
    }

    public LocalDateTime getTimestamp() {
        return timestamp != null ? timestamp : null;
    }

    public Integer getDireccionLora() {
        return direccionLora != null ? direccionLora : null;
    }

    public Double getTemperaturaPromedio() {
        return temperaturaPromedio != null ? temperaturaPromedio.doubleValue() : null;
    }

    public Double getNivelDeBateria() {
        return nivelDeBateria != null ? nivelDeBateria.doubleValue() : null;
    }

    public Integer getSuccion() {
        return succion != null ? succion : null;
    }


    public Integer getAire() {
        return aire != null ? aire : null;
    }

    public Integer getPerdida() {
        return perdida != null ? perdida : null;
    }

    public Integer getFlujoInverso() {
        return flujoInverso != null ? flujoInverso : null;
    }

    public Integer getCaudalCero() {
        return caudalCero != null ? caudalCero : null;
    }

    public Integer getMuestrasInvalidas() {
        return muestrasInvalidas != null ? muestrasInvalidas : null;
    }

    public Double getCaudalPromedio() {
        return caudalPromedio != null ? caudalPromedio.doubleValue() : null;
    }

    public List<Double> getMedicionesDeVolumen() {
        return medicionesDeVolumen != null ? medicionesDeVolumen : null;
    }
    
    public String getDireccion() {
        return direccion != null ? direccion : null;
    }

    public Double getLatitud() {
        return latitud != null ? latitud : null;
    }

    public Double getLongitud() {
        return longitud != null ? longitud.doubleValue() : null;
    }

    public PaqueteDTO() {}

    public PaqueteDTO(Long medidorId, LocalDateTime fecha, Integer inicioPaquete, Integer finPaquete,
            LocalDateTime timestamp, Integer direccionLora, Double temperaturaPromedio, Double nivelDeBateria,
            Integer succion, Integer aire, Integer perdida, Integer flujoInverso,
            Integer caudalCero, Integer muestrasInvalidas, Double caudalPromedio, List<Double> medicionesDeVolumen,
            Double latitud, Double longitud, String direccion) {
        this.medidorId = medidorId;
        this.inicioPaquete = inicioPaquete;
        this.finPaquete = finPaquete;
        this.timestamp = timestamp;
        this.direccionLora = direccionLora;
        this.temperaturaPromedio = temperaturaPromedio;
        this.nivelDeBateria = nivelDeBateria;
        this.succion = succion;
        this.aire = aire;
        this.perdida = perdida;
        this.flujoInverso = flujoInverso;
        this.caudalCero = caudalCero;
        this.muestrasInvalidas = muestrasInvalidas;
        this.caudalPromedio = caudalPromedio;
        this.medicionesDeVolumen = medicionesDeVolumen;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
    }

}
