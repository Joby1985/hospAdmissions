package com.promedicus.admissions.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.promedicus.admissions.model.Patient;
import com.promedicus.admissions.model.Sex;

/**
 * Crud repository to interact with DB
 * 
 * @author jjob
 */
@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Override
    public Iterable<Patient> findAll();

    @Query("SELECT p FROM patient p where p.name=:name and p.dob=:dob and (:phone is null and p.phone is null OR :phone is not null and p.phone=:phone)")
    public List<Patient> checkPatientExists(@Param("name") String name,
                                      @Param("dob") Date dob,
                                      @Param("phone") String phone);

    @Query("SELECT s FROM sex s where (:sexCode is null OR s.code =:sexCode) AND (:sexName is null OR s.name =:sexName)")
    public List<Sex> checkValidSex(@Param("sexCode") String sexCode,
                                   @Param("sexName") String sexName);

    @Override
    public Patient save(Patient pat);

    @Override
    public void delete(Patient id);
}
