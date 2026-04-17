package com.finca.api.properties.domain.model.queries;

public record GetPagedPropertiesQuery(int page, int size, String sort) {
}

