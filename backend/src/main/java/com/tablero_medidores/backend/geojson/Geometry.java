package com.tablero_medidores.backend.geojson;

public class Geometry {

    private String type; // "Point", "Polygon", "MultiPolygon", etc.
    private Object coordinates; // Jackson deserializa arrays anidados a List/Double automáticamente

    public Geometry() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Object coordinates) {
        this.coordinates = coordinates;
    }
}
