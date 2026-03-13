package com.finca.api.properties.domain.model.aggregates;

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

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "coin", nullable = false)
    @Enumerated(EnumType.STRING)
    private ECoin coin;

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

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "status_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EStatusType statusType;

    @Column(name = "featured", nullable = false)
    private boolean featured;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PropertyImage> images = new ArrayList<>();

    // Empty constructor for JPA
    protected Property() {}

    // Creation constructor with business rules validations
    public Property(String title, Double price, ECoin coin, EDepartments department, EDistricts district, String address,
                    EPropertyType propertyType, EOperationType operationType, Double totalArea, Double builtArea,
                    Integer bedrooms, Integer bathrooms, Integer parkings, String description, boolean featured,
                    List<PropertyImage> images) {

        // Fields Validations
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        if(title.isBlank()) throw new IllegalArgumentException("Property title cannot be blank");

        this.price = Objects.requireNonNull(price, "Price cannot be null");
        if(price <= 0) throw new IllegalArgumentException("Property price must be greater than 0");

        this.coin = Objects.requireNonNull(coin, "Coin cannot be null");

        this.department = Objects.requireNonNull(department, "Department cannot be null");

        // District is mandatory if department is Lima, and must be null for other departments
        if (department == EDepartments.LIMA && district == null) {
            throw new IllegalArgumentException("District is required when department is Lima");
        }

        if (department != EDepartments.LIMA && district != null) {
            throw new IllegalArgumentException("District can only be set for Lima properties");
        }

        this.district = district;

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

        // Business rules for new properties
        this.statusType = EStatusType.AVAILABLE;
        this.publishedAt = LocalDateTime.now();

        this.featured = Objects.requireNonNull(featured, "Property featured cannot be null");

    }


    // Property Update
    public void update(String title, Double price, ECoin coin, EDepartments department, EDistricts district,
                       String address, EPropertyType propertyType, EOperationType operationType,
                       Double totalArea, Double builtArea, Integer bedrooms, Integer bathrooms, Integer parkings,
                       String description, EStatusType statusType, boolean featured) {

        this.title = Objects.requireNonNull(title, "Title cannot be null");
        if(title.isBlank()) throw new IllegalArgumentException("Property title cannot be blank");

        this.price = Objects.requireNonNull(price, "Price cannot be null");
        if(price <= 0) throw new IllegalArgumentException("Property price must be greater than 0");

        this.coin = Objects.requireNonNull(coin, "Coin cannot be null");

        this.department = Objects.requireNonNull(department, "Department cannot be null");

        if (department == EDepartments.LIMA && district == null) {
            throw new IllegalArgumentException("District is required when department is Lima");
        }

        if (department != EDepartments.LIMA && district != null) {
            throw new IllegalArgumentException("District can only be set for Lima properties");
        }

        this.district = district;

        this.address = Objects.requireNonNull(address, "Property address cannot be null");
        if(address.isBlank()) throw new IllegalArgumentException("Property address cannot be blank");

        this.propertyType = Objects.requireNonNull(propertyType, "Property type cannot be null");
        this.operationType = Objects.requireNonNull(operationType, "Operation type cannot be null");

        this.totalArea = Objects.requireNonNull(totalArea, "Property total area cannot be null");
        if(totalArea <= 0) throw new IllegalArgumentException("Property total area must be greater than 0");

        this.builtArea = Objects.requireNonNull(builtArea, "Property built area cannot be null");
        if(builtArea <= 0) throw new IllegalArgumentException("Property built area must be greater than 0");

        if (builtArea > totalArea)
            throw new IllegalArgumentException("Built area cannot be greater than total area");

        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.parkings = parkings;

        if (bedrooms != null && bedrooms < 0)
            throw new IllegalArgumentException("Bedrooms cannot be negative");

        if (bathrooms != null && bathrooms < 0)
            throw new IllegalArgumentException("Bathrooms cannot be negative");

        if (parkings != null && parkings < 0)
            throw new IllegalArgumentException("Parkings cannot be negative");

        this.description = Objects.requireNonNull(description, "Property description cannot be null");
        if(description.isBlank()) throw new IllegalArgumentException("Property description cannot be blank");

        this.statusType  = Objects.requireNonNull(statusType, "Status type cannot be null");

        this.featured = Objects.requireNonNull(featured, "Property featured cannot be null");

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
    }

    // Removing an image from the album; checks that the cover image is not removed if other images exist
    public void removeImageFromAlbum(Long imageId) {
        PropertyImage target = findImageById(imageId);
        if (target.isCover() && this.images.size() > 1)
            throw new IllegalArgumentException(
                    "Cannot remove the cover image while other images exist. Set a new cover first.");
        this.images.remove(target);
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
        long coverCount = 0;
        for (PropertyImage image : album) {
            if (!orders.add(image.getDisplayOrder()))
                throw new IllegalArgumentException("Display order must be unique per property");
            if (image.isCover()) coverCount++;
        }
        if (coverCount != 1)
            throw new IllegalArgumentException("Album must contain exactly one cover image");
        }
    }
