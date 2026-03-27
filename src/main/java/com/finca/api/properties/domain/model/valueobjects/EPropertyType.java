package com.finca.api.properties.domain.model.valueobjects;

public enum EPropertyType {
    APARTMENT("Apartamento"),
    MINI_APARTMENT("Mini Apartamento"),
    HOUSE("Casa"),
    LAND("Terreno"),
    COMMERCIAL("Local Comercial"),
    OFFICE("Oficina"),
    INDUSTRIAL("Industrial");

    private final String displayName;

    EPropertyType (String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
