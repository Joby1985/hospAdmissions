package com.promedicus.admissions.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promedicus.admissions.dataconversions.AdmissionAppObjectMappers;
import com.promedicus.admissions.dto.AdmissionDTO;
import com.promedicus.admissions.exceptions.AdmissionException;
import com.promedicus.admissions.exceptions.RegistrationException;
import com.promedicus.admissions.model.Admission;
import com.promedicus.admissions.model.Category;
import com.promedicus.admissions.model.Patient;
import com.promedicus.admissions.repository.AdmissionsRepository;
import com.promedicus.admissions.repository.CategoryRepository;
import com.promedicus.admissions.repository.PatientRepository;

/**
 * Service class to interact with repository.
 * 
 * @author jjob
 */
@Service
public class AdmissionsServiceImpl implements AdmissionsService {

    @Autowired
    private AdmissionsRepository admRepository;

    @Autowired
    private PatientRepository patRepository;

    @Autowired
    private RegistrationService regServ;

    @Autowired
    private CategoryRepository catRepository;

    @Autowired
    private AdmissionAppObjectMappers mapper;

    private static final Logger logger = LoggerFactory.getLogger(AdmissionsServiceImpl.class);

    @Override
    public List<AdmissionDTO> fetchAll() {
        Iterable<Admission> adms = admRepository.findAll();
        List<Admission> admissions = new ArrayList<>();
        adms.forEach(admissions::add);
        return mapper.toAdmissionDTOs(admissions);
    }

    @Override
    @Transactional
    public AdmissionDTO createAdmission(AdmissionDTO admissionDTO) throws AdmissionException {
        admissionDTO.setModifiedDate(Calendar.getInstance().getTime());
        Admission admission = mapper.toAdmission(admissionDTO);
        // Fetch back saved entity
        setPatientInfoToSaveAdmission(true, admission);
        return saveAdmission(admission);
    }

    @Override
    @Transactional
    public AdmissionDTO deleteAdmission(long id) throws AdmissionException {
        Optional<Admission> admission = admRepository.findById(id);
        if (admission == null || !admission.isPresent()) {
            throw new AdmissionException("Could not find the admission to delete. Please retry.");
        }
        Admission admToDelete = admission.get();
        admToDelete.setDod(Calendar.getInstance().getTime());
        return saveAdmission(admToDelete);
    }

    @Override
    @Transactional
    public AdmissionDTO updateAdmission(long id, AdmissionDTO modifiedAdmission)
            throws AdmissionException {
        Optional<Admission> admission = admRepository.findById(id);
        if (!admission.isPresent()) {
            throw new AdmissionException(
                    "Could not fetch the provided Admission entity for update. Please try again.");
        }
        Admission toUpdate = mapper.toAdmission(modifiedAdmission);
        Admission existingAdmission = admission.get();
        toUpdate.setId(existingAdmission.getId());

        // Fetch back saved entity
        setPatientInfoToSaveAdmission(false, toUpdate);
        return saveAdmission(toUpdate);
    }

    /**
     * Do all validations and save an admission entity.
     * 
     * @param admission
     * @return
     * @throws AdmissionException
     */
    private AdmissionDTO saveAdmission(Admission admission) throws AdmissionException {
        if (admission.getDoa() == null) {
            admission.setDoa(Calendar.getInstance().getTime());
        }
        Category category = null;
        if (admission.getCategory() != null) {
            category = catRepository.findByCategoryCode(admission.getCategory().getCategoryCode());
        }
        if (category == null) {
            throw new AdmissionException(
                    "Could not create the admission. Invalid Category Code provided.");
        }
        admission.setModifiedDate(Calendar.getInstance().getTime());
        Admission admissionSaved = admRepository.save(admission);
        if (admissionSaved == null) {
            throw new AdmissionException("Could not create the admission. Please retry.");
        }
        return mapper.toAdmissionDTO(admissionSaved);
    }

    /**
     * Fetch and set patient info in case of existing/registered patient, else register/save patient
     * before creating admission for the same
     * 
     * @param boolean
     * @param admission
     */
    private void setPatientInfoToSaveAdmission(boolean bCreateAdmission, Admission admission)
            throws RegistrationException {
        Patient patient = admission.getPatient();
        Patient validatedPatient = regServ.validatePatient(patient);

        List<Patient> patients = patRepository.checkPatientExists(
                patient.getName(), patient.getDob(), patient.getPhone());
        Patient existingPatient = null;
        if (patients != null && !patients.isEmpty()
                && (existingPatient = patients.get(0)) != null) {
            List<Admission> activeAdmissions = admRepository.findActiveAdmissionEntry(
                    existingPatient);
            Admission activeAdmission = null;
            if (activeAdmissions != null && !activeAdmissions.isEmpty()
                    && (activeAdmission = activeAdmissions.get(0)) != null) {
                admission.setId(activeAdmission.getId());
                logger.warn("Modifying existing admission for the patient: " + patient);
            }
            admission.setPatient(existingPatient);
        }
        else {
            if (bCreateAdmission) {
                patient = patRepository.save(validatedPatient);
                admission.setPatient(patient);
                logger.info("Creating patient");
            }
            else {
                throw new RegistrationException(
                        "Cannot change patient detaisl for an existing admission. Only admission parameters may be modified");
            }
        }
    }
}
