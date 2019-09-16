package com.promedicus.admissions.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promedicus.admissions.dataconversions.AdmissionAppObjectMappers;
import com.promedicus.admissions.dto.PatientDTO;
import com.promedicus.admissions.exceptions.RegistrationException;
import com.promedicus.admissions.model.Patient;
import com.promedicus.admissions.model.Sex;
import com.promedicus.admissions.repository.PatientRepository;

/**
 * Service class to interact with repository.
 * 
 * @author jjob
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private PatientRepository patRepository;

    @Autowired
    private AdmissionAppObjectMappers mapper;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Override
    public List<PatientDTO> fetchAll() throws RegistrationException {
        Iterable<Patient> patns = patRepository.findAll();
        List<Patient> patients = new ArrayList<>();
        patns.forEach(patients::add);
        return mapper.toPatientDTOs(patients);
    }

    @Override
    public Patient validatePatient(Patient patient) throws RegistrationException {
        // validate Patient data -- DOB, Sex
        if (patient.getDob() == null) {
            logger.error("Could not register the patient. DOB is mandatory.");
            throw new RegistrationException("Could not register the patient. DOB is mandatory.");
        }
        if (patient.getDob().after(Calendar.getInstance().getTime())) {
            logger.error("Could not register the patient. Invalid DOB provided.");
            throw new RegistrationException(
                    "Could not register the patient. Invalid DOB provided.");
        }
        List<Sex> fetchedSexInfo = patRepository.checkValidSex(
                patient.getSex().getCode(), patient.getSex().getName());
        if (fetchedSexInfo == null || fetchedSexInfo.isEmpty()) {
            logger.error("Could not register the patient. Invalid Sex info provided.");
            throw new RegistrationException(
                    "Could not register the patient. Invalid Sex info provided.");
        }
        patient.getSex().setCode(fetchedSexInfo.get(0).getCode());
        return patient;
    }
}
