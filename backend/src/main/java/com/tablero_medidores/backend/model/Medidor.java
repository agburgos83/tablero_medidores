package com.tablero_medidores.backend.model;

import java.util.List;

import com.tablero_medidores.backend.service.data.Barrio;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Medidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Barrio barrio;

    private String direccion;

    private Double latitud;

    private Double longitud;

    @ManyToOne
    private Operario operario;

    @OneToMany(mappedBy = "medidor")
    private List<Paquete> paquetesEnviados;

    public Medidor(Barrio barrio, String direccion, Double latitud, Double longitud, Operario operario) {
        this.barrio = barrio;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.operario = operario;
    }

    public Medidor() {
    }

    public Long getId() {
        return id;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}