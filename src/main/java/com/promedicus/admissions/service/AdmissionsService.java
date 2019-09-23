package com.promedicus.admissions.service;

import java.util.List;

import com.promedicus.admissions.dto.AdmissionDTO;
import com.promedicus.admissions.exceptions.AdmissionException;

public interface AdmissionsService {

    /**
     * Fetch all Admissions data in the system.
     * 
     * @return
     */
    public List<AdmissionDTO> fetchAll() throws AdmissionException;

    /**
     * Save an admission entry
     * 
     * @param admissionDTO
     * @return
     * @throws AdmissionException
     */
    public AdmissionDTO createAdmission(AdmissionDTO admissionDTO) throws AdmissionException;

    /**
     * Delete an admission by the given ID
     * 
     * @param id
     * @return
     * @throws AdmissionException
     */
    public AdmissionDTO deleteAdmission(long id) throws AdmissionException;

    /**
     * Update the admission entity identified by given param
     * 
     * @param id
     * @param modifiedAdmission
     * @return
     * @throws AdmissionException
     */
    public AdmissionDTO updateAdmission(long id, AdmissionDTO modifiedAdmission)
            throws AdmissionException;
    
    /**
     * Find all active admissions.
     * @return
     */
    public List<AdmissionDTO> findActiveAdmissions();
}
