package com.mainservice.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mainservice.main.entities.UserEntity;
import com.mainservice.main.repositories.UserRepository;
import com.mainservice.main.util.JWTUtil;
import com.mainservice.main.validation.UserValidation;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserValidation userValidation;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired 
	private PasswordService passwordService;
	
	@Override
	public ResponseEntity<String> register(UserEntity user) {
		String responseMessage = "";
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		try {
			if (userRepository.existsByUsername(user.getUsername())) {
	            responseMessage = "Username is already taken!";
	        }
			else if (userRepository.existsByEmail(user.getEmail())) {
		        responseMessage = "Email is already taken!";
		    }
			else {
				user.setPassword(passwordService.hashPassword(user.getPassword()));
				if(userRepository.saveUser(user)) {
					responseMessage = "User registered successfully.";
					httpStatus = HttpStatus.OK;
				}
				else {
					responseMessage  = "Unable to complete registration";
				}
			}
		}
		catch (Exception e) {
			responseMessage = e.getMessage();
		}
		return new ResponseEntity<String>(responseMessage, httpStatus);
	}
	
	@Override
	public ResponseEntity<String> login(UserEntity userEntity){
		String responseMessage = "";
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		try {
				UserEntity user = userRepository.getUserByUsername(userEntity.getUsername());
				if(user == null) {
					responseMessage = "User not found";
				}
				else if(!passwordService.verifyPassword(userEntity.getPassword(), user.getPassword())) {
					responseMessage = "Invalid credentials";
					httpStatus = HttpStatus.UNAUTHORIZED;
				}
				else{
					responseMessage = jwtUtil.generateToken(user.getUsername());
					httpStatus = HttpStatus.OK;
				}
		}
		catch(Exception e) {
			responseMessage = e.getMessage();
		}
		return new ResponseEntity<String>(responseMessage, httpStatus);
	}

}
