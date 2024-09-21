package com.dgPaf.FinalProject.service;

import com.dgPaf.FinalProject.model.Address;
import com.dgPaf.FinalProject.model.Furniture;
import com.dgPaf.FinalProject.model.Image;
import com.dgPaf.FinalProject.model.User;
import com.dgPaf.FinalProject.repository.AddressRepository;
import com.dgPaf.FinalProject.repository.FurnitureRepository;
import com.dgPaf.FinalProject.repository.ImageRepository;
import com.dgPaf.FinalProject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FurnitureService {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ImageRepository imageRepository;

    // Get all furniture items
    public List<Furniture> getAllFurnitures() {
        return furnitureRepository.findAll();
    }

    @Transactional
    public Furniture createFurniture(Furniture furniture, Long userId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        furniture.setOwner(owner);
        furniture.setUploadDate(LocalDateTime.now());
        furniture.setAvailable(true);

        if (furniture.getImage() != null) {
            Image image = furniture.getImage();
            image.setFurniture(furniture);
            imageRepository.save(image);
        }

        return furnitureRepository.save(furniture);
    }


    // Fetch all furniture items for a specific user
    public List<Furniture> getUserFurniture(Long userId) {
        return furnitureRepository.findByOwnerId(userId);
    }

    // Update furniture item
    @Transactional
    public Furniture updateFurniture(Long id, Furniture updatedFurniture) {
        Furniture existingFurniture = furnitureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Furniture not found"));

        // Update furniture properties
        existingFurniture.setTitle(updatedFurniture.getTitle());
        existingFurniture.setDescription(updatedFurniture.getDescription());
        existingFurniture.setCategory(updatedFurniture.getCategory());
        existingFurniture.setAvailable(updatedFurniture.isAvailable());

        // Handle Address update
        Address existingAddress = addressRepository.findById(updatedFurniture.getAddress().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        existingAddress.setStreet(updatedFurniture.getAddress().getStreet());
        existingAddress.setCity(updatedFurniture.getAddress().getCity());
        existingAddress.setState(updatedFurniture.getAddress().getState());
        existingAddress.setZipCode(updatedFurniture.getAddress().getZipCode());
        existingAddress.setCountry(updatedFurniture.getAddress().getCountry());

        existingFurniture.setAddress(existingAddress);

        // Handle image update if applicable
        if (updatedFurniture.getImage() != null) {
            existingFurniture.setImage(updatedFurniture.getImage());
        }

        return furnitureRepository.save(existingFurniture);
    }

    // Delete furniture item
    public void deleteFurniture(Long furnitureId, Long userId) {
        Optional<Furniture> furniture = furnitureRepository.findById(furnitureId);
        if (furniture.isPresent() && furniture.get().getOwner().getId() == userId) {
            furnitureRepository.delete(furniture.get());
        } else {
            throw new RuntimeException("Furniture not found or user not authorized");
        }
    }

    // Fetch a single furniture item
    public Furniture getFurnitureById(Long furnitureId) {
        return furnitureRepository.findById(furnitureId)
                .orElseThrow(() -> new RuntimeException("Furniture not found"));
    }

    public Furniture saveFurniture(Furniture furniture) {
        if (furniture.getImage() != null) {
            furniture.getImage().setFurniture(furniture);
        }

        // Now save the furniture, and it will cascade to the single image
        return furnitureRepository.save(furniture);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public List<Furniture> searchFurniture(String name, String category, String city) {
        return furnitureRepository.searchFurniture(name, category, city);
    }

}

