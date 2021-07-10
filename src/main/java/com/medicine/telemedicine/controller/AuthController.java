package com.medicine.telemedicine.controller;

import com.medicine.telemedicine.exception.AppException;
import com.medicine.telemedicine.exception.EntityNotFoundException;
import com.medicine.telemedicine.model.Role;
import com.medicine.telemedicine.model.RoleName;
import com.medicine.telemedicine.model.Hospital;
import com.medicine.telemedicine.model.Doctors;
import com.medicine.telemedicine.model.Patient;
import com.medicine.telemedicine.payload.ApiResponse;
import com.medicine.telemedicine.payload.JwtAuthenticationResponse;
import com.medicine.telemedicine.exception.BadRequestException;
import com.medicine.telemedicine.payload.LoginRequest;
import com.medicine.telemedicine.payload.SignUpRequestHosp;
import com.medicine.telemedicine.payload.SignUpRequestDoc;
import com.medicine.telemedicine.payload.SignUpRequestPatient;
import com.medicine.telemedicine.repository.RoleRepository;
import com.medicine.telemedicine.repository.HospitalRepository;
import com.medicine.telemedicine.security.JwtTokenProvider;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.authentication.BadCredentialsException;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import com.medicine.telemedicine.repository.DoctorRepository;
import com.medicine.telemedicine.repository.PatientRepository;


@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    DoctorRepository doctorRepository;
    
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	 try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),             
                        loginRequest.getPassword()
                )
        );
       
        SecurityContextHolder.getContext().setAuthentication(authentication);
    
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    	 } catch (BadCredentialsException exception) {
             throw new BadRequestException("Wrong username or password!");
         }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestHosp signUpRequest) {
        if(hospitalRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(hospitalRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        if(hospitalRepository.existsByPhonenumber(signUpRequest.getPhonenumber())) {
            return new ResponseEntity(new ApiResponse(false, "Phone numenr already in use!"),
                    HttpStatus.BAD_REQUEST);
        }


        // Creating user's account
        
        Hospital hosp = new Hospital(signUpRequest.getName(),signUpRequest.getEmail(), signUpRequest.getUsername(),
        		 signUpRequest.getPassword(),signUpRequest.getAddress(),signUpRequest.getRegNumber(),signUpRequest.getPhonenumber(),signUpRequest.getContactPerson(),signUpRequest.getServices(),signUpRequest.getBank(),signUpRequest.getBankcode(),signUpRequest.getUsertype());

       
        hosp.setPassword(passwordEncoder.encode(hosp.getPassword()));
        
        Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		 
/**
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
**/
	
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
			hosp.setUsertype("1");
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					hosp.setUsertype("2");
					break;
				case "Hospital":
					Role modRole = roleRepository.findByName(RoleName.ROLE_HOSPITAL)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
					hosp.setUsertype("4");
					break;
				case "pharm":
					Role phsrmRole = roleRepository.findByName(RoleName.ROLE_PHARMARCY)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(phsrmRole);
					hosp.setUsertype("5");
					break;
				case "Doctor":
					Role docRole = roleRepository.findByName(RoleName.ROLE_DOCTOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(docRole);
					hosp.setUsertype("6");
					break;
				   case "Lab":
					Role labRole = roleRepository.findByName(RoleName.ROLE_LAB)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(labRole);
					hosp.setUsertype("7");
					break;
				default:
					Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				       hosp.setUsertype(userRole.toString());
					
				}
			});
		}
        //hosp.setRoles(Collections.singleton(roles));
		
         hosp.setRoles(roles);
  
        Hospital result = hospitalRepository.save(hosp);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/hospitals/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    
    
    // Register doctors 
    
    @PostMapping("/signup2")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestDoc signUpRequest) {
        if(doctorRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(doctorRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        if(doctorRepository.existsByPhonenumber(signUpRequest.getPhonenumber())) {
            return new ResponseEntity(new ApiResponse(false, "Phone numenr already in use!"),
                    HttpStatus.BAD_REQUEST);
        }


        // Creating Doctor's account
        
        Doctors doc = new Doctors(signUpRequest.getFirst_name() ,signUpRequest.getLast_name(),signUpRequest.getMiddle_name(), signUpRequest.getDob(),signUpRequest.getPhonenumber(),signUpRequest.getEmail(),signUpRequest.getAddress(),signUpRequest.getGender(), signUpRequest.getUsername(),
        		 signUpRequest.getPassword(),signUpRequest.getUsertype(),signUpRequest.getMed_school(),signUpRequest.getYear_graduation(),signUpRequest.getPractice_no(),signUpRequest.getAffiliate_hospital(),signUpRequest.getImage(),signUpRequest.getSpecialty(),signUpRequest.getCurrent_address());

       
        doc.setPassword(passwordEncoder.encode(doc.getPassword()));
        
        Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		
        Role userRole = roleRepository.findByName(RoleName.ROLE_DOCTOR)
                .orElseThrow(() -> new AppException("User Role not set."));

	

		roles.add(userRole); 
		doc.setUsertype("6");
	    doc.setRoles(roles);
      
  
        Doctors result = doctorRepository.save(doc);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/doctors/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    
    
    // Register Patients 
    
    
    @PostMapping("/signup3")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestPatient signUpRequest) {
        if(patientRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(patientRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        if(patientRepository.existsByPhonenumber(signUpRequest.getPhonenumber())) {
            return new ResponseEntity(new ApiResponse(false, "Phone numenr already in use!"),
                    HttpStatus.BAD_REQUEST);
        }


        // Creating Patient's account
        
        Patient pat = new Patient(signUpRequest.getFirst_name() ,signUpRequest.getLast_name(),signUpRequest.getMiddle_name(),signUpRequest.getGov_id(), signUpRequest.getDob(),signUpRequest.getPhonenumber(),signUpRequest.getOther_no(),signUpRequest.getEmail(),signUpRequest.getAddress(),signUpRequest.getGender(), signUpRequest.getAllergies(),signUpRequest.getWeight(),signUpRequest.getHeight(),signUpRequest.getBlood_group(),signUpRequest.getGenotype(),signUpRequest.getUsername(),
        		 signUpRequest.getPassword(),signUpRequest.getUsertype());

       
        pat.setPassword(passwordEncoder.encode(pat.getPassword()));
        
        Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		
        Role userRole = roleRepository.findByName(RoleName.ROLE_PATIENT)
                .orElseThrow(() -> new AppException("User Role not set."));

	

		roles.add(userRole); 
		pat.setUsertype("3");
		pat.setRoles(roles);
      
  
        Patient result = patientRepository.save(pat);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/patinets/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    
    
    
    @GetMapping(path = {"/hosp/{id}"})
    public ResponseEntity<?> findById(@PathVariable long id){
      return hospitalRepository.findById(id)
              .map(record -> ResponseEntity.ok().body(record))
              .orElseThrow(() -> new EntityNotFoundException(Hospital.class, "id", String.valueOf(id)));
    }
    
    @DeleteMapping(path ={"/hosp/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
      return hospitalRepository.findById(id)
          .map(record -> {
        	  hospitalRepository.deleteById(id);
              return ResponseEntity.ok().build();
          }).orElseThrow(() -> new RuntimeException("User id not found"));
    }
    
   
    @PutMapping(value="hosp/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id,
            @RequestBody Hospital hospital){
      return hospitalRepository.findById(id)
          .map(record -> {
        	  record.setAddress(hospital.getAddress());
        	  record.setContactPerson(hospital.getContactPerson());
        	  Hospital updated = hospitalRepository.save(record);
              return ResponseEntity.ok().body(updated);
          }).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping(path = {"/user/{usertype}"})
    public ResponseEntity<?> findByUserType(@PathVariable String usertype ,@RequestBody Hospital hospital){
       //hospitalRepository.findByUsertype(usertype);
    	List<Hospital> updated = hospitalRepository.findByUsertype(usertype);
       return ResponseEntity.ok().body(updated);
       
     
    }
    
    @GetMapping(path = {"/count/{usertype}"})
    public ResponseEntity<?> countByUserType(@PathVariable String usertype){
       long count = hospitalRepository.countByUsertype(usertype);
       //return ResponseEntity.ok().build();
       return ResponseEntity.ok().body(count);
    		   
       
     
    }
    
   //findByUsername

    @GetMapping(path = {"/hosp"})
    public ResponseEntity<?> findUsers(@Valid @RequestBody LoginRequest loginRequest){
     // return hospitalRepository.findByUsername();
      
    	List<Hospital> updated = hospitalRepository.findByUsername(loginRequest.getUsernameOrEmail());
        return ResponseEntity.ok().body(updated);
              
    }
    
    
    @GetMapping(path = {"/hosp/{username}"})
    public ResponseEntity<?> findUsers2(@PathVariable String username){
     // return hospitalRepository.findByUsername();
      
    	List<Hospital> updated = hospitalRepository.findByUsername(username);
        return ResponseEntity.ok().body(updated);
              
    }
  /**  
    @GetMapping(path = {"/hospby/{username}"})
    public ResponseEntity<?> findUsersByEmail(@PathVariable String username){

    	Optional<Hospital> updated = hospitalRepository.findByUsernameOrEmail(username,username);
    	Hospital hosp = updated.get();
        return ResponseEntity.ok().body(hosp);
              
    }
  
    @GetMapping(path ={"/hospby/{username}"})
    public ResponseEntity<?> findUsersByEmail2(@PathVariable String username) {
      
          .map(record -> {
        	  Hospital hosp =  hospitalRepository.findByUsernameOrEmail(username,username);
           return ResponseEntity.ok().body(updated);
          }).orElseThrow(() -> new RuntimeException("User id not found"));
    }
      **/
    @GetMapping(path = {"/hospby/{username}"})
    public ResponseEntity<?> findById2(@PathVariable String username){
      return hospitalRepository.findByUsernameOrEmail(username,username)
              .map(record -> ResponseEntity.ok().body(record))
              .orElseThrow(() -> new EntityNotFoundException(Hospital.class, "id",username));
    }
    
}