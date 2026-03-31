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
                command.priceDollars(),
                command.priceSoles(),
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
                command.statusType(),
                command.tags(),
                command.documentationUrl(),
                images
        );
        return Optional.of(propertyRepository.save(property));
    }

    @Override
    public Optional<Property> handle(UpdatePropertyCommand command) {
        var propertyId = command.propertyId();

        return propertyRepository.findById(propertyId).map(property -> {
            property.updateProperty(command);

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

    @Override
    public void addImage(Long propertyId, AddImageToAlbumCommand command) {

        var property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // 👉 AQUÍ usas tu lógica ya existente
        var image = new PropertyImage(
                command.fileName(),
                command.filePath(),
                command.displayOrder(),
                command.isCover()
        );

        property.addImageToAlbum(image);

        propertyRepository.save(property);
    }

}
