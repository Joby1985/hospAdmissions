package com.promedicus.admissions.controller;

import com.promedicus.admissions.dto.AdmissionDTO;

import lombok.Data;

@Data
/**
 * This class encapsulates the output result of the rest APIs, with any corresponding object
 * 
 * @author jjob
 */
public class OperationResponse {

    private int statusCode;
    private String statusMessage;
    private AdmissionDTO admission;

    public OperationResponse(int statusCode, String statusMessage, AdmissionDTO admission) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.admission = admission;
    }
}
