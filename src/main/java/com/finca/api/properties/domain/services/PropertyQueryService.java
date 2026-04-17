package com.finca.api.properties.domain.services;

import com.finca.api.properties.domain.model.aggregates.Property;
import com.finca.api.properties.domain.model.queries.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PropertyQueryService {
        List<Property> handle(GetAllPropertiesQuery query);
        List<Property> handle(GetFeaturedPropertiesQuery query);
        Optional<Property> handle(GetPropertyByIdQuery query);
        List<Property> handle(SearchedPropertiesQuery query);
        Page<Property> handle(GetPagedPropertiesQuery query);
}
