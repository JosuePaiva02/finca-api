package com.finca.api.properties.domain.model.entities;

import com.finca.api.properties.domain.model.aggregates.Property;
import com.finca.api.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;

import java.util.Objects;

@Getter
@Entity
@Table(
        name = "property_images",
        uniqueConstraints = @UniqueConstraint(name = "uk_property_images_property_order", columnNames = {"property_id", "display_order"}),
        indexes = @Index(name = "idx_property_images_property_id", columnList = "property_id")
)
public class PropertyImage extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private boolean isCover;

    protected PropertyImage() {
    }

    public PropertyImage(Property property, String fileName, String filePath, Integer displayOrder, Boolean isCover) {
        this.property = Objects.requireNonNull(property, "Property cannot be null");
        this.fileName = requireText(fileName, "File name cannot be null or blank");
        this.filePath = requireText(filePath, "File path cannot be null or blank");
        this.displayOrder = requireNonNegative(displayOrder, "Display order cannot be negative");
        this.isCover = Boolean.TRUE.equals(isCover);
    }

    public void setAsCover() {
        this.isCover = true;
    }

    public void unsetAsCover() {
        this.isCover = false;
    }

    public void updateDisplayOrder(Integer displayOrder) {
        this.displayOrder = requireNonNegative(displayOrder, "Display order cannot be negative");
    }

    private static String requireText(String value, String message) {
        Objects.requireNonNull(value, message);
        if (value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    private static Integer requireNonNegative(Integer value, String message) {
        Objects.requireNonNull(value, message);
        if (value < 0) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }
}
