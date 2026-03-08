package com.finca.api.properties.domain.model.aggregates;


import com.finca.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Entity
@Table(name = "properties")
public class Property extends AuditableAbstractAggregateRoot<Property> {



}
