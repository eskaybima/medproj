package com.medicine.telemedicine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicine.telemedicine.model.Doctors;
import com.medicine.telemedicine.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	Optional<Doctors> findByEmail(String email);

    Optional<Doctors> findByUsernameOrEmail(String username, String email);

    List<Doctors> findByIdIn(List<Long> userIds);
    Optional<Patient> findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    
    Boolean existsByPhonenumber(String phonenumber);
    
    List<Patient> findByUsertype(String usertype);
    long countByUsertype(String usertype);
}
