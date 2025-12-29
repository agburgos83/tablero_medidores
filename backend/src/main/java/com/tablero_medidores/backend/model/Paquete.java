package com.tablero_medidores.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Medidor medidor;

    private LocalDateTime fecha;

    @OneToOne(mappedBy = "paquete", cascade = CascadeType.ALL)
    private Medicion medicion;

    @Lob
    private String data;

    public Paquete(String data, Medidor medidor, LocalDateTime fecha) {
        this.data = data;
        this.medidor = medidor;
        this.fecha = fecha;
    }

    public Paquete() {}

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public Medidor getMedidor() {
        return medidor;
    }

    public void setMedidor(Medidor med) {
        this.medidor = med;
    }

    public void setData(String encodedData) {
        this.data = encodedData;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
