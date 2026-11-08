package com.liandrejohn.propertymanagement.property;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping
    public List<PropertyResponse> findAll(
            Authentication authentication
    ) {
        return propertyService.findAll(authentication.getName());
    }

    @GetMapping("/{id}")
    public PropertyResponse findById(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return propertyService.findById(
                id,
                authentication.getName()
        );
    }

    @PostMapping
    public ResponseEntity<PropertyResponse> create(
            @Valid @RequestBody PropertyRequest request,
            Authentication authentication
    ) {
        PropertyResponse property = propertyService.create(
                request,
                authentication.getName()
        );

        return ResponseEntity
                .created(URI.create("/api/properties/" + property.id()))
                .body(property);
    }

    @PutMapping("/{id}")
    public PropertyResponse update(
            @PathVariable Long id,
            @Valid @RequestBody PropertyRequest request,
            Authentication authentication
    ) {
        return propertyService.update(
                id,
                request,
                authentication.getName()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            Authentication authentication
    ) {
        propertyService.delete(id, authentication.getName());

        return ResponseEntity.noContent().build();
    }
}