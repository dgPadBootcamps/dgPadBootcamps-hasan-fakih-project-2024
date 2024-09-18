package com.dgPaf.FinalProject.dto;

import com.dgPaf.FinalProject.model.Furniture;

import java.util.List;

public class UserActivityDto {
    private List<Furniture> uploadedItems;
    private List<Furniture> donatedItems;
    private long itemsDonatedCount;
    private long peopleHelpedCount;

    public UserActivityDto(List<Furniture> uploadedItems, List<Furniture> donatedItems, long itemsDonatedCount, long peopleHelpedCount) {
        this.uploadedItems = uploadedItems;
        this.donatedItems = donatedItems;
        this.itemsDonatedCount = itemsDonatedCount;
        this.peopleHelpedCount = peopleHelpedCount;
    }

    public List<Furniture> getUploadedItems() {
        return uploadedItems;
    }

    public void setUploadedItems(List<Furniture> uploadedItems) {
        this.uploadedItems = uploadedItems;
    }

    public List<Furniture> getDonatedItems() {
        return donatedItems;
    }

    public void setDonatedItems(List<Furniture> donatedItems) {
        this.donatedItems = donatedItems;
    }

    public long getItemsDonatedCount() {
        return itemsDonatedCount;
    }

    public void setItemsDonatedCount(long itemsDonatedCount) {
        this.itemsDonatedCount = itemsDonatedCount;
    }

    public long getPeopleHelpedCount() {
        return peopleHelpedCount;
    }

    public void setPeopleHelpedCount(long peopleHelpedCount) {
        this.peopleHelpedCount = peopleHelpedCount;
    }
}
