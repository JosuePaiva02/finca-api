package com.finca.api.properties.interfaces.rest.resources;

import com.finca.api.properties.domain.model.valueobjects.EDepartments;
import com.finca.api.properties.domain.model.valueobjects.EDistricts;
import com.finca.api.properties.domain.model.valueobjects.EOperationType;
import com.finca.api.properties.domain.model.valueobjects.EPropertyType;
import com.finca.api.properties.domain.model.valueobjects.EStatusType;
import com.finca.api.properties.domain.model.valueobjects.ETags;

import java.util.List;
import java.util.Set;

public record CreatePropertyResource(
        String title,
        Double priceDollars,
        Double priceSoles,
        EDepartments department,
        EDistricts district,
        String address,
        EPropertyType propertyType,
        EOperationType operationType,
        Double totalArea,
        Double builtArea,
        Integer bedrooms,
        Integer bathrooms,
        Integer parkings,
        String description,
        EStatusType statusType,
        String documentationUrl,
        Set<ETags> tags,
        boolean featured,
        List<CreatePropertyImageResource> images
) {
}
