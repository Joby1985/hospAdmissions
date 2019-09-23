package com.promedicus.admissions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


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

    /**
     * For enabling CORS access from React application
     * 
     * @return
     */
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/admissions").allowedOrigins("http://localhost:3000");
            }
        };
    }
}
