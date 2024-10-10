package com.mainservice.main.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}
	
	public String hashPassword(String plainPassword) {
		return passwordEncoder().encode(plainPassword);
	}
	
	public Boolean verifyPassword(String plainPassword, String hashedPassword) {
		return passwordEncoder().matches(plainPassword, hashedPassword);
	}
}
