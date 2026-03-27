package com.finca.api.properties.interfaces.rest.transform;

import com.finca.api.properties.domain.model.commands.UpdateImageFromAlbumCommand;
import com.finca.api.properties.interfaces.rest.resources.UpdateImageResource;

import java.util.Objects;

public class UpdatePropertyImageCommandFromResourceAssembler {

    public static UpdateImageFromAlbumCommand toCommandFromResource(UpdateImageResource resource) {

        Objects.requireNonNull(resource, "UpdateImageResource cannot be null");

        return new UpdateImageFromAlbumCommand(
                resource.imageId(),
                resource.fileName(),
                resource.filePath(),
                resource.displayOrder(),
                resource.cover()
        );
    }
}