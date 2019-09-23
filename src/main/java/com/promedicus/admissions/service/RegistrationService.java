package com.promedicus.admissions.service;

import java.util.List;

import com.promedicus.admissions.dto.CategoryDTO;
import com.promedicus.admissions.dto.PatientDTO;
import com.promedicus.admissions.dto.SexDTO;
import com.promedicus.admissions.exceptions.AdmissionException;
import com.promedicus.admissions.exceptions.RegistrationException;
import com.promedicus.admissions.model.Patient;

public interface RegistrationService {

    /**
     * Fetch all Patients data in the system.
     * 
     * @return
     */
    public List<PatientDTO> fetchAll() throws RegistrationException;

    /**
     * Create a the patient data before saving
     * 
     * @param PatientDTO
     * @return
     * @throws AdmissionException
     */
    public Patient validatePatient(Patient patient) throws RegistrationException;

    /**
     * Fetch all valid sex details.
     * 
     * @return
     * @throws RegistrationException
     */
    public List<SexDTO> fetchAllValidSex() throws RegistrationException;

    /**
     * Fetch all valid sex details.
     * 
     * @return
     * @throws RegistrationException
     */
    public List<CategoryDTO> fetchAllValidCategories() throws RegistrationException;
}
