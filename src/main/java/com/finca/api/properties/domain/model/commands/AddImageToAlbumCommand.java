    package com.finca.api.properties.domain.model.commands;

    public record AddImageToAlbumCommand(
            String fileName,
            String filePath,
            Integer displayOrder,
            boolean isCover
    ) {}
