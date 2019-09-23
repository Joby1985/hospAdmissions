package com.promedicus.admissions.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PatientDTO {

    @NotNull(message = "Patient name cannot be null")
    private String name;

    // For automatic deserialization of String date in JSON to Date object
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "Patient DOB cannot be null")
    private Date dob;

    @NotNull(message = "Patient Sex cannot be null")
    private SexDTO sex;

    private String address;

    private String phone;
}
