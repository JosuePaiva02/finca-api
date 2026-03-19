    package com.finca.api.properties.domain.model.commands;

    import com.finca.api.properties.domain.model.valueobjects.*;

    import java.util.List;
    import java.util.Set;

    public record CreatePropertyCommand(
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
            String documentationUrl,
            Set <ETags> tags,
            boolean featured,
            List <CreatePropertyImageCommand> images
    ) {
        public CreatePropertyCommand {
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

            if (images == null || images.isEmpty()) {
                throw new IllegalArgumentException("Property must include at least one image");
            }

            tags = tags == null ? Set.of() : Set.copyOf(tags);
        }
    }
