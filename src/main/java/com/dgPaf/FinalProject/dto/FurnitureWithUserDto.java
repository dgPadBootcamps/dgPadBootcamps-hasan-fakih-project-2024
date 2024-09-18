package com.dgPaf.FinalProject.dto;

import com.dgPaf.FinalProject.model.Address;
import com.dgPaf.FinalProject.model.Furniture;
import com.dgPaf.FinalProject.model.Image;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<String> imageUrls;

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

        this.imageUrls = furniture.getImages() != null ?
                furniture.getImages().stream()
                        .map(Image::getImageUrl)
                        .collect(Collectors.toList()) : null;

        Address addressObj = furniture.getAddress();
        if (addressObj != null) {
            this.address = "Street: " + addressObj.getStreet() + ", City: " + addressObj.getCity();
        } else {
            this.address = "No address provided";
        }
    }

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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
