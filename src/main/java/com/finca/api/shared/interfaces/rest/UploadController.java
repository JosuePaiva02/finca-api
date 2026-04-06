package com.finca.api.shared.interfaces.rest;

import com.finca.api.shared.infrastructure.storage.FileStorageService;
import com.finca.api.shared.interfaces.rest.resources.UploadResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/uploads", produces = MediaType.APPLICATION_JSON_VALUE)
public class UploadController {

    private final FileStorageService fileStorageService;

    public UploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<UploadResource>> uploadImages(
            @RequestPart("files") List<MultipartFile> files
    ) {
        if (files == null || files.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<UploadResource> response = files.stream().map(file -> {
            String filePath = fileStorageService.store(file);

            String fileName = file.getOriginalFilename() != null
                    ? file.getOriginalFilename()
                    : "image";

            return new UploadResource(fileName, filePath);
        }).toList();

        return ResponseEntity.ok(response);
    }
}
