package com.promedicus.admissions.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

/**
 * Admission entity object. Uses lambok to create getters and setters.
 * 
 * @author jjob
 */
@Entity(name = "admission")
@Table(name="admissions")
@Data
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "patient")
    @JsonBackReference
    private Patient patient;

    @CreatedDate // Set current time during the creation of the record
    @Column(name = "doa", nullable = false, unique = false)
    private Date doa = Calendar.getInstance().getTime();

    @ManyToOne
    @JoinColumn(name = "category")
    @JsonBackReference
    private Category category;

    @Column(name = "source", nullable = false, unique = false)
    private String source;

    @LastModifiedDate // Set automatically the current time
    @Column(name = "modified_date", nullable = false, unique = false)
    private Date modifiedDate;

    @LastModifiedBy // Set automatically the logged in user name (in this case null, as there is no
                    // login)
    @Column(name = "modified_by", nullable = false, unique = false)
    private String modifiedBy;

    // Discharge date.
    @Column(name = "dod", nullable = false, unique = false)
    private Date dod;
}
