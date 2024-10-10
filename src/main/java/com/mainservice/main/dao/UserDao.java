package com.mainservice.main.dao;

import com.mainservice.main.entities.UserEntity;

public interface UserDao {
	
	public Boolean saveUser(UserEntity userEntity) throws Exception;
	
	public UserEntity getUserByUsername(String username) throws Exception;
	
	public UserEntity getUserByEmail(String email) throws Exception;
	
	public Boolean existsByUsername(String Username) throws Exception;
	
	public Boolean existsByEmail(String email) throws Exception;
	
}
