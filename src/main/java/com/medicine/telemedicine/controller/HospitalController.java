package com.medicine.telemedicine.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.medicine.telemedicine.exception.AppException;
import com.medicine.telemedicine.payload.ApiResponse;
import com.medicine.telemedicine.payload.JwtAuthenticationResponse;
import com.medicine.telemedicine.payload.LoginRequest;
import com.medicine.telemedicine.repository.HospitalRepository;
import com.medicine.telemedicine.security.JwtTokenProvider;
import com.medicine.telemedicine.payload.SignUpRequestHosp;
import com.medicine.telemedicine.model.Hospital;



@RestController
@RequestMapping("/api/hosp")
public class HospitalController {
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
	HospitalRepository  hospitalRepository ;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),             
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

	
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestHosp signUpRequest) {
   

        if(hospitalRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        if(hospitalRepository.existsByPhonenumber(signUpRequest.getPhonenumber())) {
            return new ResponseEntity(new ApiResponse(false, "Phone numenr already in use!"),
                    HttpStatus.BAD_REQUEST);
        }


        // Creating user's account
        Hospital hosp = new Hospital(signUpRequest.getName(),signUpRequest.getEmail(),signUpRequest.getUsername(),signUpRequest.getPassword(), signUpRequest.getAddress(), signUpRequest.getRegNumber(),signUpRequest.getPhonenumber(), signUpRequest.getContactPerson(), signUpRequest.getServices(),signUpRequest.getBank(),signUpRequest.getBankcode(),signUpRequest.getActive());

        hosp.setPassword(passwordEncoder.encode(hosp.getPassword()));

      

        //user.setRoles(Collections.singleton(userRole));

        Hospital result = hospitalRepository.save(hosp);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/hosp/{email}")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Hospital registered successfully"));
    }
    
       // get all hospital
    
 
    
}
