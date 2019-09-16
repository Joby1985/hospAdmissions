package com.promedicus.admissions.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.promedicus.admissions.model.Category;

/**
 * Crud repository to interact with DB
 * 
 * @author jjob
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Override
    public Iterable<Category> findAll();

    public Category findByCategoryCode(Integer categoryCode);
}
