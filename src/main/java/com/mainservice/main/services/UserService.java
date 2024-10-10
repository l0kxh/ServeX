package com.mainservice.main.services;

import org.springframework.http.ResponseEntity;

import com.mainservice.main.entities.UserEntity;

public interface UserService {
	
	public ResponseEntity<?> register(UserEntity userEntity);
	
	public ResponseEntity<?> login(UserEntity userEntity);
	
}
