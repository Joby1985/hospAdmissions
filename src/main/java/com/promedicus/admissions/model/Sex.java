package com.promedicus.admissions.model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sex entity object. Uses lambok to create getters and setters.
 * 
 * @author jjob
 */
@Entity(name = "sex")
@Table(name = "sexdefn")
@Data
// For Default constructor needed for hibernate pojo initiator
@NoArgsConstructor
public class Sex {

    @Id
    private String code;

    @Column(name = "name", length = 50, nullable = false, unique = false)
    private String name;

    @OneToMany(mappedBy = "sex", cascade = CascadeType.ALL)
    private Set<Patient> patients;

    public Sex(String sexCode, Patient... patient) {
        this.name = sexCode;
        this.patients = Stream.of(patient).collect(Collectors.toSet());
        this.patients.forEach(x -> x.setSex(this));
    }
}
