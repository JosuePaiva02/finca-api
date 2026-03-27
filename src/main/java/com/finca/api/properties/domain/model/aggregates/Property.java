package com.finca.api.properties.domain.model.aggregates;

import com.finca.api.properties.domain.model.commands.UpdatePropertyCommand;
import com.finca.api.properties.domain.model.entities.PropertyImage;
import com.finca.api.properties.domain.model.valueobjects.*;
import com.finca.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.*;

@Getter
@Entity
@Table(name = "properties")
public class Property extends AuditableAbstractAggregateRoot<Property> {

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "price_dollars", nullable = false)
    private Double priceDollars;

    @Column(name = "price_soles")
    private Double priceSoles;

    @Column(name = "department", nullable = false)
    @Enumerated(EnumType.STRING)
    private EDepartments department;

    @Column(name = "district")
    @Enumerated(EnumType.STRING)
    private EDistricts district;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "property_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EPropertyType propertyType;

    @Column(name = "operation_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EOperationType operationType;

    @Column(name = "total_area", nullable = false)
    private Double totalArea;

    @Column(name = "built_area", nullable = false)
    private Double builtArea;

    @Column(name = "bedrooms")
    private Integer bedrooms;

    @Column(name = "bathrooms")
    private Integer bathrooms;

    @Column(name = "parkings")
    private Integer parkings;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "documentation_url")
    private String documentationUrl;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "status_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EStatusType statusType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "property_tags",
            joinColumns = @JoinColumn(name = "property_id")
    )
    @Column(name = "tag")
    @Enumerated(EnumType.STRING)
    private Set<ETags> tags = new HashSet<>();

    @Column(name = "featured", nullable = false)
    private boolean featured;


    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PropertyImage> images = new ArrayList<>();

    // Empty constructor for JPA
    protected Property() {}

    // Creation constructor with business rules validations
    public Property(String title, Double priceDollars, Double priceSoles, EDepartments department, EDistricts district, String address,
                    EPropertyType propertyType, EOperationType operationType, Double totalArea, Double builtArea,
                    Integer bedrooms, Integer bathrooms, Integer parkings, String description, boolean featured,
                    EStatusType statusType, Set<ETags> tags, String documentationUrl, List<PropertyImage> images) {

        // Fields Validations
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        if (title.isBlank()) throw new IllegalArgumentException("Property title cannot be blank");

        this.priceDollars = Objects.requireNonNull(priceDollars, "Price cannot be null");
        if (priceDollars <= 0) throw new IllegalArgumentException("Property price in dollars must be greater than 0");

        this.priceSoles = priceSoles;
        if (priceSoles != null && priceSoles <= 0) {
            throw new IllegalArgumentException("Property price in soles must be greater than 0");
        }

        this.department = Objects.requireNonNull(department, "Department cannot be null");

        this.district = district;

        // District is mandatory if department is Lima, and must be null for other departments
        if (department == EDepartments.LIMA && district == null) {
            throw new IllegalArgumentException("District is required when department is Lima");
        }

        if (department != EDepartments.LIMA && district != null) {
            throw new IllegalArgumentException("District can only be set for Lima properties");
        }
        this.address = Objects.requireNonNull(address, "Property address cannot be null");
        if(address.isBlank()) throw new IllegalArgumentException("Property address cannot be blank");

        this.propertyType = Objects.requireNonNull(propertyType, "Property type cannot be null");
        this.operationType = Objects.requireNonNull(operationType, "Operation type cannot be null");

        this.totalArea = Objects.requireNonNull(totalArea, "Property total area cannot be null");
        if(totalArea <= 0) throw new IllegalArgumentException("Property total area must be greater than 0");

        this.builtArea = Objects.requireNonNull(builtArea, "Property built area cannot be null");
        if(builtArea <= 0) throw new IllegalArgumentException("Property built area must be greater than 0");

        // Consistency: total area is always greater than built area
        if (builtArea > totalArea)
            throw new IllegalArgumentException("Built area cannot be greater than total area");

        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.parkings = parkings;

        // Consistency: this spaces cannot be negative numbers
        if (bedrooms != null && bedrooms < 0)
            throw new IllegalArgumentException("Bedrooms cannot be negative");

        if (bathrooms != null && bathrooms < 0)
            throw new IllegalArgumentException("Bathrooms cannot be negative");

        if (parkings != null && parkings < 0)
            throw new IllegalArgumentException("Parkings cannot be negative");

        this.description = Objects.requireNonNull(description, "Property description cannot be null");
        if(description.isBlank()) throw new IllegalArgumentException("Property description cannot be blank");

        createAlbum(images);

        this.documentationUrl = documentationUrl;

        this.statusType = Objects.requireNonNull(statusType, "Property status type cannot be null");

        this.tags = new HashSet<>();
        if (tags != null) {
            tags.forEach(this::addTag);
        }

        // Business rules for new properties
        this.publishedAt = LocalDateTime.now();

        this.featured = featured;
    }


    // Property Update
    public void updateProperty(UpdatePropertyCommand command) {

        // Validate all fields first before applying any changes to ensure atomicity of the update operation
        String newTitle = Objects.requireNonNull(command.title(), "Title cannot be null");
        if (newTitle.isBlank()) throw new IllegalArgumentException("Property title cannot be blank");

        Double newPriceDollars = Objects.requireNonNull(command.priceDollars(), "Price cannot be null");
        if (newPriceDollars <= 0) throw new IllegalArgumentException("Property price must be greater than 0");

        Double newPriceSoles = command.priceSoles();
        if (newPriceSoles != null && newPriceSoles <= 0) {
            throw new IllegalArgumentException("Property price in soles must be greater than 0");
        }

        EDepartments newDepartment = Objects.requireNonNull(command.department(), "Department cannot be null");
        EDistricts newDistrict = command.district();

        // District is mandatory if department is Lima, and must be null for other departments
        if (newDepartment == EDepartments.LIMA && newDistrict == null) {
            throw new IllegalArgumentException("District is required when department is Lima");
        }

        if (newDepartment != EDepartments.LIMA && newDistrict != null) {
            throw new IllegalArgumentException("District can only be set for Lima properties");
        }

        String newAddress = Objects.requireNonNull(command.address(), "Property address cannot be null");
        if (newAddress.isBlank()) throw new IllegalArgumentException("Property address cannot be blank");

        EPropertyType newPropertyType = Objects.requireNonNull(command.propertyType(), "Property type cannot be null");
        EOperationType newOperationType = Objects.requireNonNull(command.operationType(), "Operation type cannot be null");

        Double newTotalArea = Objects.requireNonNull(command.totalArea(), "Property total area cannot be null");
        if (newTotalArea <= 0) throw new IllegalArgumentException("Property total area must be greater than 0");

        Double newBuiltArea = Objects.requireNonNull(command.builtArea(), "Property built area cannot be null");
        if (newBuiltArea <= 0) throw new IllegalArgumentException("Property built area must be greater than 0");

        // Consistency: total area is always greater than built area
        if (newBuiltArea > newTotalArea)
            throw new IllegalArgumentException("Built area cannot be greater than total area");

        Integer newBedrooms = command.bedrooms();
        Integer newBathrooms = command.bathrooms();
        Integer newParkings = command.parkings();

        String newDescription = Objects.requireNonNull(command.description(), "Property description cannot be null");
        if (newDescription.isBlank()) throw new IllegalArgumentException("Property description cannot be blank");

        String documentationUrl = command.documentationUrl();

        EStatusType newStatusType = Objects.requireNonNull(command.statusType(), "Status type cannot be null");

        // After all validations, update the property fields
        this.title = newTitle;
        this.priceDollars = newPriceDollars;
        this.priceSoles = newPriceSoles;
        this.department = newDepartment;
        this.district = newDistrict;
        this.address = newAddress;
        this.propertyType = newPropertyType;
        this.operationType = newOperationType;
        this.totalArea = newTotalArea;
        this.builtArea = newBuiltArea;
        this.bedrooms = newBedrooms;
        this.bathrooms = newBathrooms;
        this.parkings = newParkings;
        this.description = newDescription;
        this.documentationUrl = documentationUrl;
        this.statusType = newStatusType;
        this.featured = command.featured();
        updateTags(command.tags());
        albumUpdate(command);
    }

    //Methods

    // Tag Management
    // Adding a new tag to the property; checks for duplicates
    public void addTag(ETags tag) {
        Objects.requireNonNull(tag, "Tag cannot be null");

        boolean added = this.tags.add(tag);

        if (!added) {
            throw new IllegalArgumentException("El tag ya existe en la propiedad");
        }
    }

    // Updating tags
    public void updateTags(Set<ETags> newTags) {
        this.tags.clear();

        if (newTags != null) {
            newTags.forEach(this::addTag);
        }
    }

    // Photo Album Management
    // Adding a new image to the album; checks for unique display order and cover image rules
    public void addImageToAlbum(PropertyImage image) {
        Objects.requireNonNull(image, "Image cannot be null");
        boolean orderTaken = this.images.stream()
                .anyMatch(i -> i.getDisplayOrder().equals(image.getDisplayOrder()));
        if (orderTaken) throw new IllegalArgumentException(
                "Display order " + image.getDisplayOrder() + " is already taken in this album");
        if (image.isCover()) unsetCurrentCover();
        image.setProperty(this);
        this.images.add(image);
        validateExactlyOneCover(this.images);
    }

    // Updating an existing image in the album; checks for unique display order and cover image rules
    public void updateImageInAlbum(Long imageId, String fileName, String filePath,
                                   Integer displayOrder, boolean isCover) {
        PropertyImage target = findImageById(imageId);
        boolean orderTakenByOther = this.images.stream()
                .anyMatch(i -> !i.getId().equals(imageId)
                               && i.getDisplayOrder().equals(displayOrder));
        if (orderTakenByOther) throw new IllegalArgumentException(
                "Display order " + displayOrder + " is already taken in this album");
        if (isCover) unsetCurrentCover();
        target.update(fileName, filePath, displayOrder, isCover);
        validateExactlyOneCover(this.images);
    }

    // Removing an image from the album; checks that the cover image is not removed if other images exist
    public void removeImageFromAlbum(Long imageId) {
        PropertyImage target = findImageById(imageId);
        if (this.images.size() == 1) {
            throw new IllegalArgumentException("Property must have at least one image");
        }
        this.images.remove(target);
        validateExactlyOneCover(this.images);
    }


    // Album Creation: replaces the entire album with a new set of images; checks for unique display order and cover image rules
    public void createAlbum(List<PropertyImage> album) {
        Objects.requireNonNull(album, "Images cannot be null");
        if (album.isEmpty()) throw new IllegalArgumentException("Property must include at least one image");
        validateAlbumRules(album);
        this.images.clear();
        album.forEach(img -> { img.setProperty(this); this.images.add(img); });
    }

    private void unsetCurrentCover() {
        this.images.stream()
                .filter(PropertyImage::isCover)
                .findFirst()
                .ifPresent(PropertyImage::unsetCover);
    }

    private PropertyImage findImageById(Long imageId) {
        return this.images.stream()
                .filter(i -> i.getId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Image " + imageId + " does not belong to this property"));
    }

    private void validateAlbumRules(List<PropertyImage> album) {
        Set<Integer> orders = new HashSet<>();
        for (PropertyImage image : album) {
            if (!orders.add(image.getDisplayOrder()))
                throw new IllegalArgumentException("Display order must be unique per property");
        }
        validateExactlyOneCover(album);
        }

    private void validateExactlyOneCover(Collection<PropertyImage> images) {
        long coverCount = images.stream()
                .filter(PropertyImage::isCover)
                .count();

        if (coverCount != 1) {
            throw new IllegalStateException(
                    "Property must always have exactly one cover image");
        }
    }

    private void albumUpdate(UpdatePropertyCommand command) {
        // DELETE
        command.deletedImages().forEach(img ->
                removeImageFromAlbum(img.imageId())
        );
        // UPDATE
        command.updatedImages().forEach(img ->
                updateImageInAlbum(
                        img.imageId(),
                        img.fileName(),
                        img.filePath(),
                        img.displayOrder(),
                        Boolean.TRUE.equals(img.isCover())
                ));
        // ADD
        command.newImages().forEach(img -> {
            var image = new PropertyImage(
                    img.fileName(),
                    img.filePath(),
                    img.displayOrder(),
                    img.isCover());
            addImageToAlbum(image);
        });


    }
}
