package com.promedicus.admissions.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.promedicus.admissions.model.Admission;
import com.promedicus.admissions.model.Patient;

/**
 * Crud repository to interact with DB
 * 
 * @author jjob
 */
@Repository
public interface AdmissionsRepository extends CrudRepository<Admission, Long> {

    @Override
    public Iterable<Admission> findAll();

    @Query("SELECT a FROM admission a WHERE a.doa >= :input")
    public List<Admission> listAdmissionAfter(@Param("input") Date input);

    @Query("SELECT a FROM admission a WHERE a.dod is null and a.patient = :patient")
    public List<Admission> findActiveAdmissionEntry(@Param("patient") Patient patient);

    @Override
    public Admission save(Admission adm);

    @Override
    public void delete(Admission id);
}
