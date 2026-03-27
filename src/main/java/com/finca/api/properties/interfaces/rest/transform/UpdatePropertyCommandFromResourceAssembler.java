package com.finca.api.properties.interfaces.rest.transform;

import com.finca.api.properties.domain.model.commands.UpdatePropertyCommand;
import com.finca.api.properties.interfaces.rest.resources.CreatePropertyResource;

import java.util.List;
import java.util.Set;

public class UpdatePropertyCommandFromResourceAssembler {
    public static UpdatePropertyCommand toCommandFromResource(Long propertyId, CreatePropertyResource resource) {
        return new UpdatePropertyCommand(
                propertyId,
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
                resource.tags() != null ? Set.copyOf(resource.tags()) : Set.of(),
                resource.documentationUrl(),
                resource.featured(),
                List.of(),
                List.of(),
                List.of()
        );
    }
}
