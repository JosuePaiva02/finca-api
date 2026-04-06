package com.finca.api.properties.interfaces.rest.transform;

import com.finca.api.properties.domain.model.commands.AddImageToAlbumCommand;
import com.finca.api.properties.domain.model.commands.DeleteImageFromAlbumCommand;
import com.finca.api.properties.domain.model.commands.UpdateImageFromAlbumCommand;
import com.finca.api.properties.domain.model.commands.UpdatePropertyCommand;
import com.finca.api.properties.interfaces.rest.resources.UpdatePropertyResource;

import java.util.List;
import java.util.Set;

public class UpdatePropertyCommandFromResourceAssembler {
    public static UpdatePropertyCommand toCommandFromResource(Long propertyId, UpdatePropertyResource resource) {

        // Images mapping is done here because we need to handle the case when the lists are null and convert them to empty lists
        List<AddImageToAlbumCommand> newImages = resource.newImages() == null
                ? List.of()
                : resource.newImages().stream()
                .map(img -> new AddImageToAlbumCommand(
                        img.fileName(),
                        img.filePath(),
                        img.displayOrder(),
                        img.cover()
                ))
                .toList();

        List<UpdateImageFromAlbumCommand> updatedImages = resource.updatedImages() == null
                ? List.of()
                : resource.updatedImages().stream()
                .map(img -> new UpdateImageFromAlbumCommand(
                        img.imageId(),
                        img.fileName(),
                        img.filePath(),
                        img.displayOrder(),
                        img.cover()
                ))
                .toList();

        List<DeleteImageFromAlbumCommand> deletedImages = resource.deletedImages() == null
                ? List.of()
                : resource.deletedImages().stream()
                .map(img -> new DeleteImageFromAlbumCommand(img.imageId()))
                .toList();

        return new UpdatePropertyCommand(
                propertyId,
                resource.title(),
                resource.priceDollars(),
                resource.priceSoles(),
                resource.department(),
                resource.district(),
                resource.address(),
                resource.propertyType(),
                resource.operationType(),
                resource.totalArea(),
                resource.builtArea(),
                resource.bedrooms(),
                resource.bathrooms(),
                resource.parkings(),
                resource.description(),
                resource.statusType(),
                resource.tags() != null ? Set.copyOf(resource.tags()) : Set.of(),
                resource.documentationUrl(),
                resource.featured(),
                newImages,
                updatedImages,
                deletedImages
        );
    }
}
