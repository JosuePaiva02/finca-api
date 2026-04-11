package com.finca.api.properties.application.internal.queryservices;


import com.finca.api.properties.domain.model.aggregates.Property;
import com.finca.api.properties.domain.model.queries.*;
import com.finca.api.properties.domain.model.valueobjects.EDepartments;
import com.finca.api.properties.domain.services.PropertyQueryService;
import com.finca.api.properties.infrastructure.persistence.jpa.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyQueryServiceImpl implements PropertyQueryService {

    private final PropertyRepository propertyRepository;

    public PropertyQueryServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<Property> handle(GetAllPropertiesQuery query) {
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> handle(GetFeaturedPropertiesQuery query) {
        return propertyRepository.findByFeaturedTrue();
    }

    @Override
    public Optional<Property> handle(GetPropertyByIdQuery query) {
        return propertyRepository.findById(query.propertyId());
    }

    @Override
    public List<Property> handle(SearchedPropertiesQuery query) {
        List<Property> properties = propertyRepository.findAll();

        List<Property> result = properties.stream()
                .filter(property -> {
                    boolean matches = true;

                    // Min and Max filter in Dollars
                    if (query.minPriceDollars() != null) {
                        matches &= property.getPriceDollars() >= query.minPriceDollars();
                    }
                    if (query.maxPriceDollars() != null) {
                        matches &= property.getPriceDollars() <= query.maxPriceDollars();
                    }

                    // Min and Max filter in Soles (CAN BE NULL)
                    if (query.minPriceSoles() != null) {
                        matches &= property.getPriceSoles() != null &&
                                property.getPriceSoles() >= query.minPriceSoles();
                    }
                    if (query.maxPriceSoles() != null) {
                        matches &= property.getPriceSoles() != null &&
                                property.getPriceSoles() <= query.maxPriceSoles();
                    }

                    // Department
                    if (query.department() != null) {
                        matches &= property.getDepartment() == query.department();
                    }

                    // District (ONLY LIMA)
                    if (query.department() == EDepartments.LIMA && query.district() != null) {
                        matches &= property.getDistrict() == query.district();
                    }

                    // Property type
                    if (query.propertyType() != null) {
                        matches &= property.getPropertyType() == query.propertyType();
                    }

                    // Operation type
                    if (query.operationType() != null) {
                        matches &= property.getOperationType() == query.operationType();
                    }

                    // Status
                    if (query.statusType() != null) {
                        matches &= property.getStatusType() == query.statusType();
                    }

                    // Bedrooms
                    if (query.minBedrooms() != null) {
                        matches &= property.getBedrooms() != null &&
                                property.getBedrooms() >= query.minBedrooms();
                    }
                    if (query.maxBedrooms() != null) {
                        matches &= property.getBedrooms() != null &&
                                property.getBedrooms() <= query.maxBedrooms();
                    }

                    // Bathrooms
                    if (query.minBathrooms() != null) {
                        matches &= property.getBathrooms() != null &&
                                property.getBathrooms() >= query.minBathrooms();
                    }
                    if (query.maxBathrooms() != null) {
                        matches &= property.getBathrooms() != null &&
                                property.getBathrooms() <= query.maxBathrooms();
                    }

                    // Parkings
                    if (query.minParkings() != null) {
                        matches &= property.getParkings() != null &&
                                property.getParkings() >= query.minParkings();
                    }
                    if (query.maxParkings() != null) {
                        matches &= property.getParkings() != null &&
                                property.getParkings() <= query.maxParkings();
                    }

                    // Total area
                    if (query.minTotalArea() != null) {
                        matches &= property.getTotalArea() >= query.minTotalArea();
                    }
                    if (query.maxTotalArea() != null) {
                        matches &= property.getTotalArea() <= query.maxTotalArea();
                    }

                    // Built area
                    if (query.minBuiltArea() != null) {
                        matches &= property.getBuiltArea() >= query.minBuiltArea();
                    }
                    if (query.maxBuiltArea() != null) {
                        matches &= property.getBuiltArea() <= query.maxBuiltArea();
                    }

                    // Tags
                    if (query.tags() != null && !query.tags().isEmpty()) {
                        matches &= property.getTags().containsAll(query.tags());
                    }

                    return matches;
                })
                .toList();

        // SORTING (optional)
        if (query.sorting() != null) {
            switch (query.sorting()) {
                case PRICE_ASC -> result = result.stream()
                        .sorted(java.util.Comparator.comparing(Property::getPriceDollars))
                        .toList();

                case PRICE_DESC -> result = result.stream()
                        .sorted(java.util.Comparator.comparing(Property::getPriceDollars).reversed())
                        .toList();
            }
        }
        return result;
    }
}
