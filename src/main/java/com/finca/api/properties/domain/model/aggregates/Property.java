package com.finca.api.properties.domain.model.aggregates;

import com.finca.api.properties.domain.model.entities.PropertyImage;
import com.finca.api.properties.domain.model.valueobjects.*;
import com.finca.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Column(name = "district", nullable = false, length = 50)
    private String district;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "property_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EPropertyType propertyType;

    @Column(name = "operation_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EOperationType operationType;

    @Column(name = "area", nullable = false)
    private Double area;

    @Column(name = "bedrooms")
    private Integer bedrooms;

    @Column(name = "bathrooms")
    private Integer bathrooms;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "status_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EStatusType statusType;


    // Relation between Property and PropertyImage with cascade and orphan removal
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PropertyImage> images = new ArrayList<>();

    // Empty constructor for JPA
    protected Property() {
    }

    // Creation constructor with business rules validations
    public Property(String title, Double price, String district, String address,
                    EPropertyType propertyType, EOperationType operationType, Double area,
                    Integer bedrooms, Integer bathrooms, String description, List<PropertyImage> images) {

        // Business rules validations
        this.title = Objects.requireNonNull(title, "Property title cannot be null");
        if(title.isBlank()) throw new IllegalArgumentException("Property title cannot be blank");

        this.price = Objects.requireNonNull(price, "Property price cannot be null");
        if(price <= 0) throw new IllegalArgumentException("Property price must be greater than 0");

        this.district = Objects.requireNonNull(district, "Property district cannot be null");
        if(district.isBlank()) throw new IllegalArgumentException("Property district cannot be blank");

        this.address = Objects.requireNonNull(address, "Property address cannot be null");
        if(address.isBlank()) throw new IllegalArgumentException("Property address cannot be blank");

        this.propertyType = Objects.requireNonNull(propertyType, "Property type cannot be null");
        this.operationType = Objects.requireNonNull(operationType, "Operation type cannot be null");

        this.area = Objects.requireNonNull(area, "Property area cannot be null");
        if(area <= 0) throw new IllegalArgumentException("Property area must be greater than 0");

        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;

        // Creating an album
        createAlbum(images);

        // Business rules for new properties
        this.statusType = EStatusType.AVAILABLE;
        this.publishedAt = LocalDateTime.now();
    }

    public void createAlbum(List<PropertyImage> album) {
        Objects.requireNonNull(album, "Images cannot be null");
        if (album.isEmpty()) throw new IllegalArgumentException("Property must include at least one image");

        validateAlbumRules(album);
        this.images.clear();
        album.forEach(this::addImage);
    }

    private void addImage(PropertyImage image) {
        Objects.requireNonNull(image, "Image cannot be null");
        image.setProperty(this);
        this.images.add(image);
    }

    private void validateAlbumRules(List<PropertyImage> album) {
        Set<Integer> orders = new HashSet<>();
        long coverCount = 0;

        for (PropertyImage image : album) {
            if (!orders.add(image.getDisplayOrder())) {
                throw new IllegalArgumentException("Display order must be unique per property");
            }
            if (image.isCover()) {
                coverCount++;
            }
        }

        // Business rule: Exactly one cover image must be present
        if (coverCount != 1) {
            throw new IllegalArgumentException("Album must contain exactly one cover image");
        }
    }
}
