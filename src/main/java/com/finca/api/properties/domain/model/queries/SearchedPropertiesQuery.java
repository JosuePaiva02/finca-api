package com.finca.api.properties.domain.model.queries;

import com.finca.api.properties.domain.model.valueobjects.*;

import java.util.Set;

public record SearchedPropertiesQuery(

        // Price filters
        Double minPriceDollars,
        Double maxPriceDollars,

        Double minPriceSoles,
        Double maxPriceSoles,

        // Location filters
        EDepartments department,
        EDistricts district,

        // Property type filter
        EPropertyType propertyType,

        // Operation type filter
        EOperationType operationType,

        // Property status filter
        EStatusType statusType,

        // Bedrooms filters
        Integer minBedrooms,
        Integer maxBedrooms,

        // Bathrooms filters
        Integer minBathrooms,
        Integer maxBathrooms,

        // Parkings filters
        Integer minParkings,
        Integer maxParkings,

        // Total area filters
        Double minTotalArea,
        Double maxTotalArea,

        // Built area filters
        Double minBuiltArea,
        Double maxBuiltArea,

        // Tags filter
        Set<ETags> tags,

        //Sorting
        ESorting sorting
) {
}
