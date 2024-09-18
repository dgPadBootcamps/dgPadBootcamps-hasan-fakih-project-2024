package com.dgPaf.FinalProject.service;

import com.dgPaf.FinalProject.model.Furniture;
import com.dgPaf.FinalProject.model.Image;
import com.dgPaf.FinalProject.repository.FurnitureRepository;
import com.dgPaf.FinalProject.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FurnitureRepository furnitureRepository;

    public Image saveImageForFurniture(Long furnitureId, Image image) {
        Furniture furniture = furnitureRepository.findById(furnitureId)
                .orElseThrow(() -> new RuntimeException("Furniture not found"));

        image.setFurniture(furniture);
        return imageRepository.save(image);
    }
}

