package com.medicine.telemedicine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicine.telemedicine.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
	
	Optional<Hospital> findByEmail(String email);

    Optional<Hospital> findByUsernameOrEmail(String username, String email);

    List<Hospital> findByIdIn(List<Long> userIds);
    //Optional<Hospital> findByUsername(String username);
    List<Hospital> findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    
    Boolean existsByPhonenumber(String phonenumber);
    
    List<Hospital> findByUsertype(String usertype);
    long countByUsertype(String usertype);

}
