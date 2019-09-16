package com.promedicus.admissions.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Category entity object. Uses lambok to create getters and setters.
 * 
 * @author jjob
 */
@Data
// For Default constructor needed for hibernate pojo initiator
@NoArgsConstructor
public class CategoryDTO {

    private Integer categoryCode;

    public String categoryName;

    public CategoryDTO(Integer categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }
}
