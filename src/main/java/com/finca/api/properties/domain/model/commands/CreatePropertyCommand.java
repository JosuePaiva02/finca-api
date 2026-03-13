package com.finca.api.properties.domain.model.commands;

import com.finca.api.properties.domain.model.valueobjects.*;

import java.util.List;

public record CreatePropertyCommand(
        String title,
        Double price,
        ECoin coin,
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
        Boolean featured,
        List <CreatePropertyImageCommand> images
) {
}
