package com.promedicus.admissions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    //To allow CORS access to the 
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(
            path = "/admissions",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdmissionDTO> fetchAllAdmissions() {
        List<AdmissionDTO> admissions = admServ.fetchAll();
        log.info("Listing all admissions");
        return admissions;
    }

    @PostMapping("/admissions")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<OperationResponse> createAdmission(@RequestBody AdmissionDTO admissionDTO)
            throws Exception {
        log.debug("In createAdmission, input : " + admissionDTO.toString());
        return ResponseEntity.ok(
                new OperationResponse(
                        HttpStatus.OK.value(), "Created admission",
                        admServ.createAdmission(admissionDTO)));
    }

    @PutMapping("/admissions/{ID}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<OperationResponse> updateAdmission(@PathVariable("ID") long id,
                                                             @RequestBody AdmissionDTO admssion)
            throws Exception {
        log.debug("In updateAdmission, input : " + id);
        AdmissionDTO updatedAdmission = admServ.updateAdmission(id, admssion);
        return ResponseEntity.ok(
                new OperationResponse(
                        HttpStatus.OK.value(), "Admission updated", updatedAdmission));
    }

    @DeleteMapping("/admissions/{ID}")
    @CrossOrigin(origins = "http://localhost:3000")
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
