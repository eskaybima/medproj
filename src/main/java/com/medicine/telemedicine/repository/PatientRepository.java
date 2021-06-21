package com.medicine.telemedicine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicine.telemedicine.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	
	Optional<Patient> findByEmail(String email);

    //Optional<Patient> findByUsernameOrEmail(String username, String email);

    List<Patient> findByIdIn(List<Long> userIds);

    Boolean existsByEmail(String email);
    
    Boolean existsByPhonenumber(String phonenumber);

}
