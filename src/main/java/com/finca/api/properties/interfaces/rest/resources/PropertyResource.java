package com.finca.api.properties.interfaces.rest.resources;

import com.finca.api.properties.domain.model.valueobjects.ETags;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record PropertyResource(
        Long id,
        String title,
        Double priceDollars,
        Double priceSoles,
        String department,
        String district,
        String address,
        String propertyType,
        String operationType,
        Double totalArea,
        Double builtArea,
        Integer bedrooms,
        Integer bathrooms,
        Integer parkings,
        String description,
        String documentationUrl,
        LocalDateTime publishedAt,
        String statusType,
        Set<ETags> tags,
        boolean featured,
        List<PropertyImageResource> images
) {
}
