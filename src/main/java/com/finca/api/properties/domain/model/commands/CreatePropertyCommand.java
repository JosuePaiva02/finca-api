package com.finca.api.properties.domain.model.commands;

import com.finca.api.properties.domain.model.valueobjects.EOperationType;
import com.finca.api.properties.domain.model.valueobjects.EPropertyType;

import java.util.List;

public record CreatePropertyCommand(
        String title,
        Double price,
        String district,
        String address,
        EPropertyType propertyType,
        EOperationType operationType,
        Double area,
        Integer bedrooms,
        Integer bathrooms,
        String description,
        List <CreatePropertyImageCommand> images
) {
}
