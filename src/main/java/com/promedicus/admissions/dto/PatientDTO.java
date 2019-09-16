package com.promedicus.admissions.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PatientDTO {

    private String name;

    // For automatic deserialization of String date in JSON to Date object
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dob;

    private SexDTO sex;

    private String address;

    private String phone;
}
