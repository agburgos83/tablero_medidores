package com.tablero_medidores.backend.geojson;

import java.util.Map;

public class Feature {

    private String type;
    private Geometry geometry;
    private Map<String, Object> properties; // flexible, guarda cualquier propiedad del geojson

    public Feature() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
