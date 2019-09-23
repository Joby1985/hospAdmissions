package com.promedicus.admissions.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.promedicus.admissions.model.Category;
import com.promedicus.admissions.model.Sex;

/**
 * Crud repository to interact with DB
 * 
 * @author jjob
 */
@Repository
public interface SexRepository extends CrudRepository<Sex, Integer> {

    @Override
    public Iterable<Sex> findAll();

    public Category findByName(String name);
}
