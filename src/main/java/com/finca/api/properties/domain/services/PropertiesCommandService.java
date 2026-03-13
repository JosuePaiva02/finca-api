package com.finca.api.properties.domain.services;

import com.finca.api.properties.domain.model.aggregates.Property;
import com.finca.api.properties.domain.model.commands.CreatePropertyCommand;
import com.finca.api.properties.domain.model.commands.DeletePropertyCommand;
import com.finca.api.properties.domain.model.commands.UpdatePropertyCommand;

import java.util.Optional;

public interface PropertiesCommandService {
    Optional<Property> handle(CreatePropertyCommand command);
    Optional<Property> handle(UpdatePropertyCommand command);
    void handle(DeletePropertyCommand command);
}
