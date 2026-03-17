package com.finca.api.properties.application.internal.commandservices;

import com.finca.api.properties.domain.model.aggregates.Property;
import com.finca.api.properties.domain.model.commands.*;
import com.finca.api.properties.domain.model.entities.PropertyImage;
import com.finca.api.properties.domain.services.PropertyCommandService;
import com.finca.api.properties.infrastructure.persistence.jpa.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyCommandServiceImpl implements PropertyCommandService {

    private final PropertyRepository propertyRepository;

    public PropertyCommandServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

     @Override
    public Optional<Property> handle(CreatePropertyCommand command) {

        var images = command.images().stream().map(img -> new PropertyImage(
                img.fileName(),
                img.filePath(),
                img.displayOrder(),
                img.isCover()
        )).collect(Collectors.toList());

        Property property = new Property(
                command.title(),
                command.price(),
                command.coin(),
                command.department(),
                command.district(),
                command.address(),
                command.propertyType(),
                command.operationType(),
                command.totalArea(),
                command.builtArea(),
                command.bedrooms(),
                command.bathrooms(),
                command.parkings(),
                command.description(),
                command.featured(),
                images
        );
        return Optional.of(propertyRepository.save(property));
    }

    @Override
    public Optional<Property> handle(UpdatePropertyCommand command) {
        var propertyId = command.propertyId();
        return propertyRepository.findById(propertyId).map(property -> {
            property.updateProperty(command);

            if (command.deletedImages() != null) {
                command.deletedImages().forEach(imgCmd ->
                        property.removeImageFromAlbum(imgCmd.imageId())
                );
            }

            if (command.updatedImages() != null) {
                command.updatedImages().forEach(imgCmd -> property.updateImageInAlbum(
                        imgCmd.imageId(),
                        imgCmd.fileName(),
                        imgCmd.filePath(),
                        imgCmd.displayOrder(),
                        Boolean.TRUE.equals(imgCmd.isCover())
                ));
            }

            if (command.newImages() != null) {
                command.newImages().forEach(imgCmd -> {
                    var image = new PropertyImage(
                            imgCmd.fileName(),
                            imgCmd.filePath(),
                            imgCmd.displayOrder(),
                            imgCmd.isCover()
                    );
                    property.addImageToAlbum(image);
                });
            }

            return propertyRepository.save(property);
        });
    }

    @Override
    public void handle(DeletePropertyCommand command) {
        var propertyId = command.propertyId();
        if (!propertyRepository.existsById(propertyId)) {
            throw new IllegalArgumentException("Property with id " + propertyId + " does not exist");
        }
        propertyRepository.deleteById(propertyId);
    }
}
