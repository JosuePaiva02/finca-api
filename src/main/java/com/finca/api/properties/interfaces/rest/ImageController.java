package com.finca.api.properties.interfaces.rest;

import com.finca.api.properties.domain.model.commands.AddImageToAlbumCommand;
import com.finca.api.properties.domain.services.PropertyCommandService;
import com.finca.api.properties.interfaces.rest.resources.ImageUploadResource;
import com.finca.api.shared.infrastructure.storage.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/properties/{propertyId}/images", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Property Images", description = "Image upload and management for Property albums")
public class ImageController {

    private final PropertyCommandService propertyCommandService;
    private final FileStorageService fileStorageService;

    public ImageController(PropertyCommandService propertyCommandService,
                           FileStorageService fileStorageService) {
        this.propertyCommandService = propertyCommandService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload images for a Property",
            description = "Uploads one or more image files and returns their metadata")
    public ResponseEntity<List<ImageUploadResource>> uploadImages(
            @PathVariable Long propertyId,
            @RequestPart("files") List<MultipartFile> files
    ) {

        if (files == null || files.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ImageUploadResource> responses = files.stream().map(file -> {

            // 1. Storing the file
            String filePath = fileStorageService.store(file);

            // 2. Getting the original filename or defaulting to "image"
            String fileName = file.getOriginalFilename() != null
                    ? file.getOriginalFilename()
                    : "image";

            // 3. Create the command to add the image to the album (displayOrder and cover are set to defaults for now)
            var command = new AddImageToAlbumCommand(
                    fileName,
                    filePath,
                    null,
                    false
            );

            // 4. Execute the command to associate the image with the property
            propertyCommandService.addImage(propertyId, command);

            // 5. Return the response resource with the file metadata
            return new ImageUploadResource(fileName, filePath);

        }).toList();

        return ResponseEntity.ok(responses);
    }
}
