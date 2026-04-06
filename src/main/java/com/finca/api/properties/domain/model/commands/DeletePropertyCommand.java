package com.finca.api.properties.domain.model.commands;

public record DeletePropertyCommand(Long propertyId) {
    public DeletePropertyCommand {
        if (propertyId == null) {
            throw new IllegalArgumentException("Property id cannot be null");
        }
    }
}
