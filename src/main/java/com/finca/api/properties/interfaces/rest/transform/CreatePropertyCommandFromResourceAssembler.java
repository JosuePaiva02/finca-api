package com.finca.api.properties.interfaces.rest.transform;

import com.finca.api.properties.domain.model.commands.CreatePropertyCommand;
import com.finca.api.properties.interfaces.rest.resources.CreatePropertyResource;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CreatePropertyCommandFromResourceAssembler {
    public static CreatePropertyCommand toCommandFromResource(CreatePropertyResource resource) {
        Objects.requireNonNull(resource, "CreatePropertyResource cannot be null");
      
        return new CreatePropertyCommand(
                resource.title(),
                resource.priceDollars(),
                resource.priceSoles(),
                resource.department(),
                resource.district(),
                resource.address(),
                resource.propertyType(),
                resource.operationType(),
                resource.totalArea(),
                resource.builtArea(),
                resource.bedrooms(),
                resource.bathrooms(),
                resource.parkings(),
                resource.description(),
                resource.statusType(),
                resource.documentationUrl(),
                resource.tags() != null ? Set.copyOf(resource.tags()) : Set.of(),
                resource.featured(),
                resource.images() != null
                        ? resource.images().stream()
                            .map(CreatePropertyImageCommandFromResourceAssembler::toCommandFromResource)
                            .toList()
                        : List.of()
        );
    }
}
