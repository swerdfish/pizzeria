package com.pizzeria.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.training.models.AuthenticationRequest;
import com.pizzeria.training.models.AuthenticationResponse;
import com.pizzeria.training.models.Customer;
import com.pizzeria.training.repository.CustomerRepository;
import com.pizzeria.training.service.CustomerService;
import com.pizzeria.training.util.JwtUtil;

@RestController
public class AuthController {

	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private JwtUtil jwtUtils;

	
//	@PostMapping("/register")
//	private ResponseEntity<?> registerClient(@RequestBody AuthenticationRequest authenticationRequest ){
//		String username = authenticationRequest.getEmail();
//		String password = authenticationRequest.getPassword();
//		Customer customer = new Customer();
//		customer.setEmail(username);
//		customer.setPassword(password);
//		
//		try {
//			custRepo.save(customer);
//		}catch (Exception e){
//			 return ResponseEntity.ok(new AuthenticationResponse("error in registration: " + username));
//
//		}
//		
//		 return ResponseEntity.ok(new AuthenticationResponse("Successfully registered: " + username));
//	}
	@PostMapping("/auth")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest ){
		
		
		String username = authenticationRequest.getEmail();
		String password = authenticationRequest.getPassword();
		
		
		try {
			System.out.println(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)));
			
		}catch (BadCredentialsException e){
			return ResponseEntity.ok(new AuthenticationResponse("Username or password is Invalid" + username));
		}
		
		UserDetails loadedUser = customerService.loadUserByUsername(username);
		
		String generatedToken = jwtUtils.generateToken(loadedUser);
		
		return ResponseEntity.ok(new AuthenticationResponse(generatedToken));
	}
	
}
