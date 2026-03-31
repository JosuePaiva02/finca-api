package com.finca.api.properties.interfaces.rest;

import com.finca.api.properties.domain.model.commands.DeletePropertyCommand;
import com.finca.api.properties.domain.model.commands.UpdatePropertyCommand;
import com.finca.api.properties.domain.model.queries.GetFeaturedPropertiesQuery;
import com.finca.api.properties.domain.model.queries.GetPropertyByIdQuery;
import com.finca.api.properties.domain.model.queries.SearchedPropertiesQuery;
import com.finca.api.properties.domain.model.valueobjects.*;
import com.finca.api.properties.domain.services.PropertyCommandService;
import com.finca.api.properties.domain.services.PropertyQueryService;
import com.finca.api.properties.interfaces.rest.resources.CreatePropertyResource;
import com.finca.api.properties.interfaces.rest.resources.PropertyResource;
import com.finca.api.properties.interfaces.rest.resources.UpdatePropertyResource;
import com.finca.api.properties.interfaces.rest.transform.CreatePropertyCommandFromResourceAssembler;
import com.finca.api.properties.interfaces.rest.transform.PropertyResourceFromEntityAssembler;
import com.finca.api.properties.interfaces.rest.transform.UpdatePropertyCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/properties", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Properties", description = "Properties Management Endpoints (within Properties Bounded Context)")
public class PropertyController {
    private final PropertyCommandService propertyCommandService;
    private final PropertyQueryService propertyQueryService;

    public PropertyController(PropertyCommandService propertyCommandService, PropertyQueryService propertyQueryService) {
        this.propertyCommandService = propertyCommandService;
        this.propertyQueryService = propertyQueryService;
    }

    // POST OPERATIONS
    @PostMapping
    @Operation(summary = "Create a new Property Entry", description = "Creates a new Property Entry with the provided data")
    public ResponseEntity<PropertyResource> createProperty(@RequestBody @Valid CreatePropertyResource resource) {
        var command = CreatePropertyCommandFromResourceAssembler.toCommandFromResource(resource);
        var property = propertyCommandService.handle(command)
                .orElseThrow(() -> new RuntimeException("Property could not be created"));
        var propertyResource = PropertyResourceFromEntityAssembler.fromEntity(property);
        return ResponseEntity.created(URI.create("/api/v1/properties/" + property.getId())).body(propertyResource);
    }

    // GET OPERATIONS
    @GetMapping("/{id}")
    @Operation(summary = "Get a Property Entry by ID", description = "Reads a property data by its ID")
    public ResponseEntity<PropertyResource> getPropertyById(@PathVariable Long id) {
        var getPropertyByIdQuery = new GetPropertyByIdQuery(id);
        var optionalProperty = this.propertyQueryService.handle(getPropertyByIdQuery);
        return optionalProperty
                .map(property -> ResponseEntity.ok(PropertyResourceFromEntityAssembler.fromEntity(property)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/featured")
    @Operation(summary = "Get all featured Properties", description = "Retrieves all Properties marked as featured")
    public ResponseEntity<List<PropertyResource>> getFeaturedProperties() {
        var query = new GetFeaturedPropertiesQuery();
        var properties = this.propertyQueryService.handle(query);
        var resources = properties.stream()
                .map(PropertyResourceFromEntityAssembler::fromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/search")
    @Operation(summary = "Search Properties by criteria", description = "Searches for Properties matching the provided filters")
    public ResponseEntity<List<PropertyResource>> searchProperties(
            @RequestParam(required = false) Double minPriceDollars, @RequestParam(required = false) Double maxPriceDollars,
            @RequestParam(required = false) Double minPriceSoles, @RequestParam(required = false) Double maxPriceSoles,
            @RequestParam(required = false) EDepartments department, @RequestParam(required = false) EDistricts district,
            @RequestParam(required = false) EPropertyType propertyType, @RequestParam(required = false) EOperationType operationType, @RequestParam(required = false) EStatusType statusType,
            @RequestParam(required = false) Integer minBedrooms, @RequestParam(required = false) Integer maxBedrooms,
            @RequestParam(required = false) Integer minBathrooms, @RequestParam(required = false) Integer maxBathrooms,
            @RequestParam(required = false) Integer minParkings, @RequestParam(required = false) Integer maxParkings,
            @RequestParam(required = false) Double minTotalArea, @RequestParam(required = false) Double maxTotalArea,
            @RequestParam(required = false) Double minBuiltArea, @RequestParam(required = false) Double maxBuiltArea,
            @RequestParam(required = false) Set<ETags> tags, @RequestParam(required = false) ESorting sorting
    ) {
        var query = new SearchedPropertiesQuery(minPriceDollars, maxPriceDollars, minPriceSoles, maxPriceSoles,
                department, district, propertyType, operationType, statusType,
                minBedrooms, maxBedrooms, minBathrooms, maxBathrooms, minParkings, maxParkings,
                minTotalArea, maxTotalArea, minBuiltArea, maxBuiltArea, tags, sorting
        );

        var properties = this.propertyQueryService.handle(query);
        var resources = properties.stream()
                .map(PropertyResourceFromEntityAssembler::fromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    // DELETE OPERATIONS
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an Property Entry", description = "Delete an Property Entry by its ID")
    public ResponseEntity<?> deletePropertyById(@PathVariable Long id) {
        this.propertyCommandService.handle(new DeletePropertyCommand(id));
        return ResponseEntity.noContent().build();
    }

    // PUT OPERATIONS
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Property Entry", description = "Updates an existing Property")
    public ResponseEntity<PropertyResource> updateProperty(
            @PathVariable Long id,
            @RequestBody @Valid UpdatePropertyResource resource
    ) {
        var command = UpdatePropertyCommandFromResourceAssembler.toCommandFromResource(id, resource);

        var updatedProperty = propertyCommandService.handle(command)
                .orElseThrow(() -> new RuntimeException("Property not found or could not be updated"));

        var response = PropertyResourceFromEntityAssembler.fromEntity(updatedProperty);
        return ResponseEntity.ok(response);
    }



}
