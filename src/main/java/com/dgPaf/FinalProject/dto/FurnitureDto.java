package com.dgPaf.FinalProject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class FurnitureDto {

    private long id;
    private String title;

    @Column(length = 1000)
    private List<String> images;

    private String description;
}
