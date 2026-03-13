package com.finca.api.properties.domain.model.entities;

import com.finca.api.properties.domain.model.aggregates.Property;
import com.finca.api.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Getter
@Entity
@Table(
        // Unique constraint to ensure that each image for a property has a unique display order
        name = "property_images",
        uniqueConstraints = @UniqueConstraint(name = "uk_property_images_property_order", columnNames = {"property_id", "display_order"}),
        indexes = @Index(name = "idx_property_images_property_id", columnList = "property_id")
)
public class PropertyImage extends AuditableModel {

    // Auditable Model does not create an ID, so we need to add it here
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One relationship with Property, with lazy loading and non-nullable foreign key
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(name = "file_name", nullable = false, length = 150)
    private String fileName;

    @Column(name = "file_path", nullable = false, length = 500)
    private String filePath;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @Column(name = "is_cover", nullable = false)
    private boolean cover;

    protected PropertyImage() {
    }

    public PropertyImage(String fileName, String filePath, Integer displayOrder, Boolean isCover) {

        // Fields Validations
        this.fileName = Objects.requireNonNull(fileName, "Property file name cannot be null");
        if(fileName.isBlank()) throw new IllegalArgumentException("Property file name cannot be blank");

        this.filePath = Objects.requireNonNull(filePath, "Property file path cannot be null");
        if(filePath.isBlank()) throw new IllegalArgumentException("Property file path cannot be blank");

        this.displayOrder = Objects.requireNonNull(displayOrder, "Property image display order cannot be null");
        if(displayOrder < 0) throw new IllegalArgumentException("Property image display order cannot be negative");

        this.cover = Boolean.TRUE.equals(isCover);
    }

    public void setProperty(Property property) {
        this.property = Objects.requireNonNull(property, "Property cannot be null");
    }

    // Update method to modify image details; includes validations for non-null and non-blank fields, and non-negative display order
    public void update(String fileName, String filePath, Integer displayOrder, boolean isCover) {
        this.fileName     = Objects.requireNonNull(fileName, "File name cannot be null");
        if (fileName.isBlank()) throw new IllegalArgumentException("File name cannot be blank");
        this.filePath     = Objects.requireNonNull(filePath, "File path cannot be null");
        if (filePath.isBlank()) throw new IllegalArgumentException("File path cannot be blank");
        this.displayOrder = Objects.requireNonNull(displayOrder, "Display order cannot be null");
        if (displayOrder < 0) throw new IllegalArgumentException("Display order cannot be negative");
        this.cover        = isCover;
    }

    public void unsetCover() {
        this.cover = false;
    }
}
