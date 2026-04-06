package com.finca.api.properties.domain.model.valueobjects;

public enum EOperationType {
    SALE("Venta"),
    RENT("Alquiler");

    private final String displayName;

    EOperationType (String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
