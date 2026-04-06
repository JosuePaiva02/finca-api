package com.finca.api.properties.interfaces.rest.resources;

public record PropertyImageResource(
        Long id,
        String fileName,
        String filePath,
        Integer displayOrder,
        boolean cover
) {
}
