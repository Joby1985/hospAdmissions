package com.promedicus.admissions.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Category entity object. Uses lambok to create getters and setters.
 * 
 * @author jjob
 */
@Entity(name = "category")
@Table(name = "categories")
@Data
// For Default constructor needed for hibernate pojo initiator
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "category_code")
    private Integer categoryCode;

    @Column(name = "category_name")
    public String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Admission> admissions;

    public Category(Integer categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }
    /*
     * public Category(Integer catCode, Admission... adm) { this.categoryCode = catCode;
     * this.admissions = Stream.of(adm).collect(Collectors.toSet()); this.admissions.forEach(x ->
     * x.setCategory(this)); }
     */
}
