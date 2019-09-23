package com.promedicus.admissions.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Admissions DTO object. Using lambok to avoid boilerplate getter/setter methods.
 * 
 * @author jjob
 */
@Data
public class AdmissionDTO {

    private Long id;

    @NotNull(message = "Patient details cannot be null")
    private PatientDTO patient;

    // For automatic deserialization of String date in JSON to Date object
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "Admission date cannot be null")
    private Date admissionDate;

    @NotNull(message = "Admission Category cannot be null")
    private CategoryDTO category;

    private String source;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date modifiedDate;

    private String modifiedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dischargeDate;
}
