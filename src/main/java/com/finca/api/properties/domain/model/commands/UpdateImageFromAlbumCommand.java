package com.finca.api.properties.domain.model.commands;

import java.util.Objects;

public record UpdateImageFromAlbumCommand(
        Long imageId,
        String fileName,
        String filePath,
        Integer displayOrder,
        Boolean isCover
) {
    public UpdateImageFromAlbumCommand {
        if (imageId == null || imageId <= 0) {
            throw new IllegalArgumentException("Image id must be greater than 0");
        }
    }
}
