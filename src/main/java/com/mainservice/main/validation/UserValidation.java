package com.mainservice.main.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mainservice.main.entities.UserEntity;
import com.mainservice.main.repositories.UserRepository;

@Component
public class UserValidation {
	
	@Autowired
	private UserRepository userRepository;
	
	public String validateUsernameForLogin(String userName) {
		if(userName == null || userName.length() == 0) {
			return "Username cannot be null";
		}
		if(userName.length() < 5) {
			return "Username length must be atleast 5";
		}
		return "";
	}

	public String validateUsername(String userName) {
		try {
			if(userName == null || userName.length() == 0) {
				return "Username cannot be null";
			}
			if(userName.length() < 5) {
				return "Username length must be atleast 5";
			}
			if(userRepository.getUserByUsername(userName) != null) {
				return "Username is already taken.";
			}
			return "";
		}
		catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String validateEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
		try {
			if(email == null || email.length() == 0) {
				return "Email cannot be null.";
			}
			Pattern pattern = Pattern.compile(emailRegex);
			Matcher matcher = pattern.matcher(email);
			if(!matcher.matches()) {
				return "Invalid email address.";
			}
			if(userRepository.getUserByEmail(email) != null) {
				return "Email is already registered with us.";
			}
			return "";
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String validatePassword(String password) {
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
		if(password == null || password.length() == 0) {
			return "Password cannot be null.";
		}
		if(password.length() < 6) {
			return "Password size must be atleast 6.";
		}
		Pattern pattern = Pattern.compile(passwordRegex);
		Matcher matcher = pattern.matcher(password);
		if(!matcher.matches()) {
			return "Invalid password";
		}
		return "";
	}
	
	
	public String validateUser(UserEntity userEntity) {
		String validationMessage = "";
		validationMessage = validateUsername(userEntity.getUsername());
		if(validationMessage.length() > 0) return validationMessage;
		validationMessage = validateEmail(userEntity.getEmail());
		if(validationMessage.length() > 0) return validationMessage;
		validationMessage = validatePassword(userEntity.getPassword());
		if(validationMessage.length() > 0) return validationMessage;
		return validationMessage;
	}
}
