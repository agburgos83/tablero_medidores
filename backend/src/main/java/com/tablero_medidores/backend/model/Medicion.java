package com.tablero_medidores.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    Paquete paquete;

    // datos asociados
    private Long medidorId;
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

    public Medicion(Long medidorId, LocalDateTime fecha, String direccion, Double latitud, 
            Double longitud, Integer inicioPaquete, Integer finPaquete, LocalDateTime timestamp, 
            Integer direccionLora, Double temperaturaPromedio, Double nivelDeBateria, Integer aire, 
            Integer succion, Integer perdida,
            Integer flujoInverso, Integer caudalCero, Integer muestrasInvalidas,
            Double caudalPromedio, List<Double> medicionesDeVolumen) {

        this.medidorId = medidorId;
        this.fecha = fecha;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;

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

    }

    public Long getId() {
        return id != null ? id : null;
    }

    public Long getMedidorId() {
        return medidorId != null ? medidorId : null;
    }

    public LocalDateTime getFecha() {
        return fecha != null ? fecha : null;
    }

    public String getDireccion() {
        return direccion != null ? direccion : null;
    }

    public Double getLatitud() {
        return latitud != null ? latitud : null;
    }

    public Double getLongitud() {
        return longitud != null ? longitud : null;
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

    public Double getAire() {
        return aire != null ? aire.doubleValue() : null;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Medicion other = (Medicion) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (inicioPaquete == null) {
            if (other.inicioPaquete != null)
                return false;
        } else if (!inicioPaquete.equals(other.inicioPaquete))
            return false;
        if (finPaquete == null) {
            if (other.finPaquete != null)
                return false;
        } else if (!finPaquete.equals(other.finPaquete))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (temperaturaPromedio == null) {
            if (other.temperaturaPromedio != null)
                return false;
        } else if (!temperaturaPromedio.equals(other.temperaturaPromedio))
            return false;
        if (nivelDeBateria == null) {
            if (other.nivelDeBateria != null)
                return false;
        } else if (!nivelDeBateria.equals(other.nivelDeBateria))
            return false;
        if (succion == null) {
            if (other.succion != null)
                return false;
        } else if (!succion.equals(other.succion))
            return false;
        if (aire == null) {
            if (other.aire != null)
                return false;
        } else if (!aire.equals(other.aire))
            return false;
        if (perdida == null) {
            if (other.perdida != null)
                return false;
        } else if (!perdida.equals(other.perdida))
            return false;
        if (flujoInverso == null) {
            if (other.flujoInverso != null)
                return false;
        } else if (!flujoInverso.equals(other.flujoInverso))
            return false;
        if (caudalCero == null) {
            if (other.caudalCero != null)
                return false;
        } else if (!caudalCero.equals(other.caudalCero))
            return false;
        if (muestrasInvalidas == null) {
            if (other.muestrasInvalidas != null)
                return false;
        } else if (!muestrasInvalidas.equals(other.muestrasInvalidas))
            return false;
        if (caudalPromedio == null) {
            if (other.caudalPromedio != null)
                return false;
        } else if (!caudalPromedio.equals(other.caudalPromedio))
            return false;
        if (medicionesDeVolumen == null) {
            if (other.medicionesDeVolumen != null)
                return false;
        } else if (!medicionesDeVolumen.equals(other.medicionesDeVolumen))
            return false;
        if (latitud == null) {
            if (other.latitud != null)
                return false;
        } else if (!latitud.equals(other.latitud))
            return false;
        if (longitud == null) {
            if (other.longitud != null)
                return false;
        } else if (!longitud.equals(other.longitud))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((inicioPaquete == null) ? 0 : inicioPaquete.hashCode());
        result = prime * result + ((finPaquete == null) ? 0 : finPaquete.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((temperaturaPromedio == null) ? 0 : temperaturaPromedio.hashCode());
        result = prime * result + ((nivelDeBateria == null) ? 0 : nivelDeBateria.hashCode());
        result = prime * result + ((succion == null) ? 0 : succion.hashCode());
        result = prime * result + ((aire == null) ? 0 : aire.hashCode());
        result = prime * result + ((perdida == null) ? 0 : perdida.hashCode());
        result = prime * result + ((flujoInverso == null) ? 0 : flujoInverso.hashCode());
        result = prime * result + ((caudalCero == null) ? 0 : caudalCero.hashCode());
        result = prime * result + ((muestrasInvalidas == null) ? 0 : muestrasInvalidas.hashCode());
        result = prime * result + ((caudalPromedio == null) ? 0 : caudalPromedio.hashCode());
        result = prime * result + ((medicionesDeVolumen == null) ? 0 : medicionesDeVolumen.hashCode());
        result = prime * result + ((latitud == null) ? 0 : latitud.hashCode());
        result = prime * result + ((longitud == null) ? 0 : longitud.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Medicion [id=" + id + ", inicioPaquete=" + inicioPaquete + ", finPaquete=" + finPaquete + ", timestamp="
                + timestamp + ", temperaturaPromedio=" + temperaturaPromedio + ", nivelDeBateria=" + nivelDeBateria
                + ", succion=" + succion + ", aire=" + aire + ", perdida=" + perdida + ", flujoInverso=" + flujoInverso
                + ", caudalCero=" + caudalCero + ", muestrasInvalidas=" + muestrasInvalidas + ", caudalPromedio="
                + caudalPromedio + ", medicionesDeVolumen=" + medicionesDeVolumen + ", latitud=" + latitud
                + ", longitud=" + longitud + ", getAire()=" + getAire() + ", getCaudalCero()=" + getCaudalCero()
                + ", getCaudalPromedio()=" + getCaudalPromedio() + ", getFinPaquete()=" + getFinPaquete()
                + ", getFlujoInverso()=" + getFlujoInverso() + ", getId()=" + getId() + ", getInicioPaquete()="
                + getInicioPaquete() + ", getLatitud()=" + getLatitud() + ", getLongitud()=" + getLongitud()
                + ", getMedicionesDeVolumen()=" + getMedicionesDeVolumen() + ", getMuestraInvalida()="
                + getMuestrasInvalidas() + ", getNivelDeBateria()=" + getNivelDeBateria() + ", getPerdida()="
                + getPerdida() + ", getSuccion()=" + getSuccion() + ", getTemperaturaPromedio()="
                + getTemperaturaPromedio() + ", getTimestamp()=" + getTimestamp() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

}