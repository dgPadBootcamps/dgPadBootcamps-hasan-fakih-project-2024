package com.dgPaf.FinalProject.controller;

import com.dgPaf.FinalProject.dto.FurnitureWithUserDto;
import com.dgPaf.FinalProject.model.Furniture;
import com.dgPaf.FinalProject.model.Image;
import com.dgPaf.FinalProject.service.FurnitureService;
import com.dgPaf.FinalProject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/furniture")
public class FurnitureController {

    @Autowired
    private FurnitureService furnitureService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/all")
    public ResponseEntity<List<FurnitureWithUserDto>> getAllFurnitures() {
        List<FurnitureWithUserDto> furnitureWithUserDtos = furnitureService.getAllFurnitures().stream()
                .map(FurnitureWithUserDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(furnitureWithUserDtos);
    }
    // Create furniture
    @PostMapping("/create/{userId}")
    public ResponseEntity<Furniture> createFurniture(@RequestBody Furniture furniture, @PathVariable Long userId) {
        try {
            Furniture createdFurniture = furnitureService.createFurniture(furniture, userId);
            return ResponseEntity.ok(createdFurniture);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get all furniture items for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Furniture>> getUserFurniture(@PathVariable Long userId) {
        List<Furniture> furnitureList = furnitureService.getUserFurniture(userId);
        return ResponseEntity.ok(furnitureList);
    }

    // Update a furniture item
    @PutMapping("/update/{id}")
    public ResponseEntity<Furniture> updateFurniture(@PathVariable Long id, @RequestBody Furniture furniture) {
        try {
            Furniture updatedFurniture = furnitureService.updateFurniture(id, furniture);
            return ResponseEntity.ok(updatedFurniture);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // Delete a furniture item
    @DeleteMapping("/delete/{furnitureId}/{userId}")
    public ResponseEntity<Void> deleteFurniture(@PathVariable Long furnitureId, @PathVariable Long userId) {
        furnitureService.deleteFurniture(furnitureId, userId);
        return ResponseEntity.noContent().build();
    }

    // Get a single furniture item by ID
    @GetMapping("/{furnitureId}")
    public ResponseEntity<Furniture> getFurnitureById(@PathVariable Long furnitureId) {
        Furniture furniture = furnitureService.getFurnitureById(furnitureId);
        return ResponseEntity.ok(furniture);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Furniture>> searchFurniture(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String city) {
        List<Furniture> results = furnitureService.searchFurniture(name, category, city);
        return ResponseEntity.ok(results);
    }
}
