package com.tablero_medidores.backend.service.data;

public enum Barrio {
    EL_PELIGRO("El Peligro"),
    ARTURO_SEGUI("Arturo Seguí"),
    VILLA_ELISA("Villa Elisa"),
    ABASTO("Abasto"),
    MELCHOR_ROMERO("Melchor Romero"),
    CITY_BELL("City Bell"),
    MANUEL_B_GONNET("Manuel B. Gonnet"),
    JOAQUIN_GORINA("Joaquín Gorina"),
    JOSE_HERNANDEZ("José Hernández"),
    RINGUELET("Ringuelet"),
    TOLOSA("Tolosa"),
    LA_PLATA("La Plata"),
    SAN_CARLOS("San Carlos"),
    VILLA_ELVIRA("Villa Elvira"),
    ALTOS_DE_SAN_LORENZO("Altos de San Lorenzo"),
    EDUARDO_ARANA("Eduardo Arana"),
    LOS_HORNOS("Los Hornos"),
    LISANDRO_OLMOS("Lisandro Olmos"),
    ANGEL_ETCHEVERRY("Ángel Etcheverry"),
    VILLA_GARIBALDI_PARQUE_SICARDI("Villa Garibaldi - Parque Sicardi"),
    EL_RINCON("El Rincón"),
    LOS_PORTENOS("Los Porteños"),
    VILLA_CASTELLS("Villa Castells"),
    COLONIA_URQUIZA("Colonia Urquiza");

    private final String displayName;

    Barrio(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}