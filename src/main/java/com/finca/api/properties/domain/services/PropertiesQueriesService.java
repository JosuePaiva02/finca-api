package com.finca.api.properties.domain.services;

import com.finca.api.properties.domain.model.aggregates.Property;
import com.finca.api.properties.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface PropertiesQueriesService {
        List<Property> handle(GetFeaturedPropertiesQuery query);
        Optional<Property> handle(GetPropertyByIdQuery query);
        List<Property> handle(SearchedPropertiesQuery query);
}
