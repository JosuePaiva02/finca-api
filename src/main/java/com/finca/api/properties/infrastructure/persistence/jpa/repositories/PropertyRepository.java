package com.finca.api.properties.infrastructure.persistence.jpa.repositories;

import com.finca.api.properties.domain.model.aggregates.Property;
import com.finca.api.properties.domain.model.valueobjects.ETags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByFeaturedTrue();

    @Query("""
        SELECT p FROM Property p
        JOIN p.tags t
        WHERE t IN :tags
        GROUP BY p.id
        HAVING COUNT(DISTINCT t) = :tagCount
    """)
    List<Property> findByAllTags(Set<ETags> tags, long tagCount);
}
