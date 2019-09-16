package com.promedicus.admissions.dataconversions;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.promedicus.admissions.dto.AdmissionDTO;
import com.promedicus.admissions.dto.PatientDTO;
import com.promedicus.admissions.model.Admission;
import com.promedicus.admissions.model.Patient;

/**
 * Mapper used to transform between entity and DTO objects
 * 
 * @author jjob
 */
// Instruct MapStruct to use Spring’s dependency injection
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AdmissionAppObjectMappers {

    @Mapping(source = "dod", target = "dischargeDate")
    @Mapping(source = "doa", target = "admissionDate")
    AdmissionDTO toAdmissionDTO(Admission admission);

    List<AdmissionDTO> toAdmissionDTOs(List<Admission> admissions);

    @Mapping(source = "dischargeDate", target = "dod")
    @Mapping(source = "admissionDate", target = "doa")
    Admission toAdmission(AdmissionDTO admissionDTO);

    PatientDTO toPatientDTO(Patient patient);

    List<PatientDTO> toPatientDTOs(List<Patient> patients);

    Patient toPatient(PatientDTO patientDTO);
}
