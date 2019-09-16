package com.promedicus.admissions.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

/**
 * Patient entity object. Uses lambok to create getters and setters.
 * 
 * @author jjob
 */
@Entity(name = "patient")
@Table(name = "patient")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    
    @Column(name = "Name", length = 50, nullable = false, unique = false)
    private String name;

    @Column(name = "DOB", nullable = false, unique = false)
    private Date dob;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Admission> admissions;

    @ManyToOne
    @JoinColumn(name = "SEX")
    private Sex sex;

    @Column(name = "ADDRESS", nullable = true, unique = false)
    private String address;

    @Column(name = "PHONE", nullable = true, unique = false)
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || !(o instanceof Patient))
            return false;
        Patient other = (Patient) o;
        if (other.getName() == null && this.getName() != null
                || other.getName() != null && !other.getName().equals(this.getName())
                || other.getDob() == null && this.getDob() != null
                || other.getDob() != null && !other.getDob().equals(this.getDob())
                || other.getPhone() == null && this.getPhone() != null
                || other.getPhone() != null && !other.getPhone().equals(this.getPhone()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + (getName() == null ? 0 : getName().hashCode());
        hash = 31 * hash + (getDob() == null ? 0 : getDob().hashCode());
        hash = 31 * hash + (getPhone() == null ? 0 : getPhone().hashCode());
        return hash;
    }
}
