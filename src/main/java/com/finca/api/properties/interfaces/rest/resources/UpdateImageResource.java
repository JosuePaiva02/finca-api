package com.finca.api.properties.interfaces.rest.resources;

public record UpdateImageResource(
        Long imageId,
        String fileName,
        String filePath,
        Integer displayOrder,
        boolean cover
) {}
