package com.finca.api.properties.infrastructure.persistence.jpa.repositories;

import com.finca.api.properties.domain.model.aggregates.Property;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PropertiesRepository extends JpaRepository<Property, Long> {



}
