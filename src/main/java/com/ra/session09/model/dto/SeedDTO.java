package com.ra.session09.model.dto;

import com.ra.session09.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SeedDTO {
    private String seedName;
    private int quantity;
    private long categoryId;
}
