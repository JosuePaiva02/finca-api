package com.finca.api.properties.interfaces.rest.transform;

import com.finca.api.properties.domain.model.aggregates.Property;
import com.finca.api.properties.interfaces.rest.resources.PropertyResource;

public class PropertyResourceFromEntityAssembler {
    public static PropertyResource fromEntity(Property entity) {
        return new PropertyResource(
                entity.getId(),
                entity.getTitle(),
                entity.getPriceDollars(),
                entity.getPriceSoles(),
                entity.getDepartment().getDisplayName(),
                entity.getDistrict() != null ? entity.getDistrict().getDisplayName() : null,
                entity.getAddress(),
                entity.getPropertyType().getDisplayName(),
                entity.getOperationType().getDisplayName(),
                entity.getTotalArea(),
                entity.getBuiltArea(),
                entity.getBedrooms(),
                entity.getBathrooms(),
                entity.getParkings(),
                entity.getDescription(),
                entity.getDocumentationUrl(),
                entity.getPublishedAt(),
                entity.getStatusType().getDisplayName(),
                entity.getTags(),
                entity.isFeatured(),
                PropertyImageResourceFromEntityAssembler.fromEntities(entity.getImages())
        );
    }
}
