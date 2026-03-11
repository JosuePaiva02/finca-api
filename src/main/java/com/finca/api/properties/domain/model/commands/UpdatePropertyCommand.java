package com.finca.api.properties.domain.model.commands;

import com.finca.api.properties.domain.model.valueobjects.EOperationType;
import com.finca.api.properties.domain.model.valueobjects.EPropertyType;
import com.finca.api.properties.domain.model.valueobjects.EStatusType;

import java.util.List;

public record UpdatePropertyCommand(
        Long propertyId,
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
        EStatusType statusType,

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
    }
}
