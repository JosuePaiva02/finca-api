package com.finca.api.properties.domain.model.commands;

import com.finca.api.properties.domain.model.valueobjects.*;

import java.util.List;
import java.util.Set;

public record UpdatePropertyCommand(
        Long propertyId,
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
        List<AddImageToAlbumCommand> newImages,
        List<UpdateImageFromAlbumCommand> updatedImages,
        List<DeleteImageFromAlbumCommand> deletedImages
) {
    public UpdatePropertyCommand {
        if (propertyId == null || propertyId <= 0) {
            throw new IllegalArgumentException("Property id must be greater than 0");
        }
        newImages = newImages == null ? List.of() : List.copyOf(newImages);
        updatedImages = updatedImages == null ? List.of() : List.copyOf(updatedImages);
        deletedImages = deletedImages == null ? List.of() : List.copyOf(deletedImages);

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be null or blank");
        }
        if (priceDollars == null) {
            throw new IllegalArgumentException("Price must not be null");
        }

        if (department == null) {
            throw new IllegalArgumentException("Department must not be null");
        }

        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address must not be null or blank");
        }

        if (propertyType == null) {
            throw new IllegalArgumentException("Property type must not be null");
        }

        if (operationType == null) {
            throw new IllegalArgumentException("Operation type must not be null");
        }

        if (totalArea == null) {
            throw new IllegalArgumentException("Total area must not be null");
        }

        if (builtArea == null) {
            throw new IllegalArgumentException("Built area must not be null");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description must not be null or blank");
        }

        if (statusType == null) {
            throw new IllegalArgumentException("Status type must not be null");
        }
    }
}
