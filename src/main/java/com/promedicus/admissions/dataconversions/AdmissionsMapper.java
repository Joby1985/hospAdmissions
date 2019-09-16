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
public interface AdmissionsMapper {

    // @Mapping(source = "category.categoryName", target = "category")
    // @Mapping(source = "patient.sex.name", target = "patient.sex")
    /*
     * AdmissionDTO toAdmissionDTO(Admission admission); List<AdmissionDTO>
     * toAdmissionDTOs(List<Admission> admissions); // @Mapping(source = "category", target =
     * "category.categoryName") // @Mapping(source = "patient.sex", target = "patient.sex.name")
     * Admission toAdmission(AdmissionDTO admissionDTO);
     */
}
