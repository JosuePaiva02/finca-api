package com.finca.api.properties.interfaces.rest.resources;

public record CreatePropertyImageResource(
        String fileName,
        String filePath,
        Integer displayOrder,
        boolean cover
) {}
