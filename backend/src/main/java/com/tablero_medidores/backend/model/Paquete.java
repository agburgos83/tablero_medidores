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

    private LocalDateTime timestamp;

    // @OneToOne(mappedBy = "paquete", cascade = CascadeType.ALL)
    // private Medicion medicion;

    @Lob
    private String data;

    public Paquete(String data, Medidor medidor, LocalDateTime timestamp) {
        this.data = data;
        this.medidor = medidor;
        this.timestamp = timestamp;
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

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setMedidor(Medidor med) {
        this.medidor = med;
    }

    public void setData(String encodedData) {
        this.data = encodedData;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
