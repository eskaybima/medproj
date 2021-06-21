package com.medicine.telemedicine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicine.telemedicine.model.Doctors;


@Repository
public interface DoctorRepository extends JpaRepository<Doctors, Long> {
	Optional<Doctors> findByEmail(String email);

    Optional<Doctors> findByUsernameOrEmail(String username, String email);

    List<Doctors> findByIdIn(List<Long> userIds);
    Optional<Doctors> findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    
    Boolean existsByPhonenumber(String phonenumber);
    
    List<Doctors> findByUsertype(String usertype);
    long countByUsertype(String usertype);

}
