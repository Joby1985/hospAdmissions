package com.promedicus.admissions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.promedicus.admissions.dto.AdmissionDTO;
import com.promedicus.admissions.service.AdmissionsServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller
 * 
 * @author jjob
 */
@RestController
@EnableAutoConfiguration
@Slf4j
public class AdmissionsController {

    @Autowired
    private AdmissionsServiceImpl admServ;

    @GetMapping("/")
    String home() {
        return "Welcome to Promedicus Hospital admissions System!";
    }

    @GetMapping("/admissions")
    public ResponseEntity<List<AdmissionDTO>> fetchAllAdmissions() {
        List<AdmissionDTO> admissions = admServ.fetchAll();
        log.debug("Listing all admissions");
        return ResponseEntity.ok(admissions);
    }

    @PostMapping("/admissions")
    public ResponseEntity<OperationResponse> createAdmission(@RequestBody AdmissionDTO admissionDTO)
            throws Exception {
        log.debug("In createAdmission, input : " + admissionDTO.toString());
        return ResponseEntity.ok(
                new OperationResponse(
                        HttpStatus.OK.value(), "Created admission",
                        admServ.createAdmission(admissionDTO)));
    }

    @DeleteMapping("/admissions/{ID}")
    public ResponseEntity<OperationResponse> deleteAdmission(@PathVariable("ID") long id)
            throws Exception {
        log.debug("In deleteAdmission, input : " + id);
        AdmissionDTO closedAdmission = admServ.deleteAdmission(id);
        return ResponseEntity.ok(
                new OperationResponse(
                        HttpStatus.OK.value(), "Admission closed",
                        admServ.createAdmission(closedAdmission)));
    }
}
