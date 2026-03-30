package com.finca.api.properties.interfaces.rest.resources;

import com.finca.api.properties.domain.model.valueobjects.EDepartments;
import com.finca.api.properties.domain.model.valueobjects.EDistricts;
import com.finca.api.properties.domain.model.valueobjects.EOperationType;
import com.finca.api.properties.domain.model.valueobjects.EPropertyType;
import com.finca.api.properties.domain.model.valueobjects.EStatusType;
import com.finca.api.properties.domain.model.valueobjects.ETags;

import java.util.List;
import java.util.Set;

public record UpdatePropertyResource(
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
        Set<ETags> tags,
        String documentationUrl,
        boolean featured,
        List<NewImageResource> newImages,
        List<UpdateImageResource> updatedImages,
        List<DeleteImageResource> deletedImages
) { }
