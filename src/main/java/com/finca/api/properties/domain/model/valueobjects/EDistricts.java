package com.finca.api.properties.domain.model.valueobjects;

public enum EDistricts {
    ANCON("Ancón"),
    ATE("Ate"),
    BARRANCO("Barranco"),
    BREÑA("Breña"),
    CARABAYLLO("Carabayllo"),
    CHACLACAYO("Chaclacayo"),
    CERCADO("Cercado de Lima"),
    CHORRILLOS("Chorrillos"),
    CIENEGUILLA("Cieneguilla"),
    COMAS("Comas"),
    EL_AGUSTINO("El Agustino"),
    INDEPENDENCIA("Independencia"),
    JESUS_MARIA("Jesus María"),
    LA_MOLINA("La Molina"),
    LA_VICTORIA("La Victoria"),
    LURIGANCHO("Lurigancho"),
    LURIN("Lurín"),
    MAGDALENA_DEL_MAR("Magdalena del Mar"),
    MIRAFLORES("Miraflores"),
    PACHACAMAC("Pachacamac"),
    PUCUSANA("Pucusana"),
    PUEBLO_LIBRE("Pueblo Libre"),
    PUENTE_PIEDRA("Puente Piedra"),
    PUNTA_HERMOSA("Punta Hermosa"),
    PUNTA_NEGRA("Punta Negra"),
    RIMAC("Rímac"),
    SAN_BARTOLO("San Bartolo"),
    SAN_BORJA("San Borja"),
    SAN_ISIDRO("San Isidro"),
    SAN_JUAN_DE_LURIGANCHO("San Juan de Lurigancho"),
    SAN_JUAN_DE_MIRAFLORES("San Juan de Miraflores"),
    SAN_LUIS("San Luis"),
    SAN_MARTIN_DE_PORRES("San Martín de Porres"),
    SAN_MIGUEL("San Miguel"),
    SANTA_ANITA("Santa Anita"),
    SANTA_MARIA_DEL_MAR("Santa María del Mar"),
    SANTA_ROSA("Santa Rosa"),
    SANTIAGO_DE_SURCO("Santiago de Surco"),
    SURQUILLO("Surquillo"),
    VILLA_EL_SALVADOR("Villa El Salvador"),
    VILLA_MARIA_DEL_TRIUNFO("Villa María del Triunfo");

    private final String displayName;

    EDistricts (String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
