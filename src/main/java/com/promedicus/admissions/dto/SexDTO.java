package com.promedicus.admissions.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sex entity object. Uses lambok to create getters and setters.
 * 
 * @author jjob
 */
@Data
// For Default constructor needed for hibernate pojo initiator
@NoArgsConstructor
public class SexDTO {

    private String code;

    private String name;

    public SexDTO(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
