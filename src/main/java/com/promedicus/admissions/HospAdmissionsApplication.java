package com.promedicus.admissions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hospital admissions spring boot application
 * 
 * @author jjob
 */
@SpringBootApplication
@ComponentScan("com.promedicus.*")
public class HospAdmissionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospAdmissionsApplication.class, args);
    }
}
