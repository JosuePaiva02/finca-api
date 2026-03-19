package com.finca.api.properties.domain.model.queries;

import com.finca.api.properties.domain.model.valueobjects.*;

public record SearchedPropertiesQuery(

        //Price filters
        Double minPrice,
        Double maxPrice,

        //Location filters
        EDepartments department,
        EDistricts district,

        //Property type filter
        EPropertyType propertyType,

        //Operation type filter
        EOperationType operationType,

        //Bedrooms filters
        Integer minBedrooms,
        Integer maxBedrooms,

        //Bathrooms filters
        Integer minBathrooms,
        Integer maxBathrooms,

        //Parkings filters
        Integer minParkings,
        Integer maxParkings,

        //Total area filters
        Double minTotalArea,
        Double maxTotalArea,

        //Built area filters
        Double minBuiltArea,
        Double maxBuiltArea,

        //Sorting
        ESorting sorting
) {
}
