package com.dgPaf.FinalProject.dto;

import com.dgPaf.FinalProject.model.Address;
import com.dgPaf.FinalProject.model.Furniture;

import java.time.LocalDateTime;

public class FurnitureWithUserDto {

    private long furnitureId;
    private String title;
    private String description;
    private String category;
    private boolean available;
    private LocalDateTime uploadDate;
    private String address;

    private long userId;
    private String userName;
    private String userEmail;

    // Changed from List<String> to a single String for one image URL
    private String imageUrl;

    // Constructor
    public FurnitureWithUserDto(Furniture furniture) {
        this.furnitureId = furniture.getId();
        this.title = furniture.getTitle();
        this.description = furniture.getDescription();
        this.category = furniture.getCategory();
        this.available = furniture.isAvailable();
        this.uploadDate = furniture.getUploadDate();

        // User info
        this.userId = furniture.getOwner().getId();
        this.userName = furniture.getOwner().getFullName();
        this.userEmail = furniture.getOwner().getEmail();

        // Since there's only one image now, we just get the first one (if it exists)
        if (furniture.getImage() != null) {
            this.imageUrl = furniture.getImage().getImageUrl();
        } else {
            this.imageUrl = null;  // No image available
        }

        // Address information
        Address addressObj = furniture.getAddress();
        if (addressObj != null) {
            this.address = "Street: " + addressObj.getStreet() + ", City: " + addressObj.getCity();
        } else {
            this.address = "No address provided";
        }
    }

    // Getters and setters for all fields

    public long getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(long furnitureId) {
        this.furnitureId = furnitureId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter and setter for the single image URL
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
