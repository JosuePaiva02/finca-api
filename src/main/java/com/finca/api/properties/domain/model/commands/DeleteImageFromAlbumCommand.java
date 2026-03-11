package com.finca.api.properties.domain.model.commands;

public record DeleteImageFromAlbumCommand(
        Long imageId
) {
    public DeleteImageFromAlbumCommand {
        if (imageId == null || imageId <= 0) {
            throw new IllegalArgumentException("Image id must be greater than 0");
        }
    }
}
