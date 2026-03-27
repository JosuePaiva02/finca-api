package com.finca.api.properties.domain.model.valueobjects;

public enum EStatusType {
    A("Nuevo"),
    B("Seminuevo"),
    C("A remodelar");

    private final String displayName;

    EStatusType (String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
