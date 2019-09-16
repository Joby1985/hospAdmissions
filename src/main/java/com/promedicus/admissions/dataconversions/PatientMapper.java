package com.promedicus.admissions.dataconversions;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper used to transform between entity and DTO objects
 * 
 * @author jjob
 */
// Instruct MapStruct to use Spring’s dependency injection
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PatientMapper {

    /*
     * PatientDTO toPatientDTO(Patient patient); List<PatientDTO> toPatientDTOs(List<Patient>
     * patients); Patient toPatient(PatientDTO patientDTO);
     */
}
