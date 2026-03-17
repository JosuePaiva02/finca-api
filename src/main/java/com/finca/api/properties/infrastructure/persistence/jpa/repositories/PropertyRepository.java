package com.finca.api.properties.infrastructure.persistence.jpa.repositories;

import com.finca.api.properties.domain.model.aggregates.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
