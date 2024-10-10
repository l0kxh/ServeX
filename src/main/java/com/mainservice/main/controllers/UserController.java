package com.mainservice.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainservice.main.entities.UserEntity;
import com.mainservice.main.services.UserService;

@RestController
@RequestMapping("/user/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserEntity userEntity){
		System.out.println(userEntity.toString());
		return userService.register(userEntity);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserEntity userEntity){
		return userService.login(userEntity);
	}
	
}
