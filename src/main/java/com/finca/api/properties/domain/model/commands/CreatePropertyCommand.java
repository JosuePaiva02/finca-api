package com.finca.api.properties.domain.model.commands;

import com.finca.api.properties.domain.model.valueobjects.*;

import java.util.List;

public record CreatePropertyCommand(
        String title,
        Double price,
        ECoin coin,
        EDistricts district,
        EDepartments department,
        String address,
        EPropertyType propertyType,
        EOperationType operationType,
        Double totalArea,
        Double builtArea,
        Integer bedrooms,
        Integer bathrooms,
        Integer parkings,
        String description,
        List <CreatePropertyImageCommand> images
) {
}
