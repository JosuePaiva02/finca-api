package com.finca.api.properties.interfaces.rest.transform;

import com.finca.api.properties.domain.model.commands.CreatePropertyImageCommand;
import com.finca.api.properties.interfaces.rest.resources.CreatePropertyImageResource;

import java.util.Objects;

public class CreatePropertyImageCommandFromResourceAssembler {
    public static CreatePropertyImageCommand toCommandFromResource(CreatePropertyImageResource resource) {

        Objects.requireNonNull(resource, "CreatePropertyImageResource cannot be null");

        return new CreatePropertyImageCommand(
                resource.fileName(),
                resource.filePath(),
                resource.displayOrder(),
                resource.cover()
        );
    }
}
