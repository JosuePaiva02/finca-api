package com.finca.api.properties.domain.model.commands;

public record CreatePropertyImageCommand(
        String fileName,
        String filePath,
        Integer displayOrder,
        Boolean isCover
) {
}
