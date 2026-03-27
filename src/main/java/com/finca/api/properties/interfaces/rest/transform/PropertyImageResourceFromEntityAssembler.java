package com.finca.api.properties.interfaces.rest.transform;

import com.finca.api.properties.domain.model.entities.PropertyImage;
import com.finca.api.properties.interfaces.rest.resources.PropertyImageResource;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PropertyImageResourceFromEntityAssembler {

    public static PropertyImageResource fromEntity(PropertyImage entity) {
        Objects.requireNonNull(entity, "PropertyImage entity cannot be null");

        return new PropertyImageResource(
                entity.getId(),
                entity.getFileName(),
                entity.getFilePath(),
                entity.getDisplayOrder(),
                entity.isCover()
        );
    }

    public static List<PropertyImageResource> fromEntities(Collection<PropertyImage> images) {
        if (images == null || images.isEmpty()) {
            return List.of();
        }

        return safeStream(images)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(PropertyImage::getDisplayOrder))
                .map(PropertyImageResourceFromEntityAssembler::fromEntity)
                .toList();
    }

    private static Stream<PropertyImage> safeStream(Collection<PropertyImage> images) {
        return images == null ? Stream.empty() : images.stream();
    }
}

