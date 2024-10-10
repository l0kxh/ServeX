package com.mainservice.main.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mainservice.main.dao.UserDao;
import com.mainservice.main.entities.UserEntity;
import com.mainservice.main.mapper.UserEntityMapper;

@Repository
public class UserRepository implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Boolean saveUser(UserEntity userEntity) throws Exception {
		String sql = "INSERT INTO USERS(username, email, password, firstName, lastName, roleId, buisnessId) values(?, ?, ?, ?, ?, ?, ?)";
		int updated = jdbcTemplate.update(sql, 
				userEntity.getUsername(), 
				userEntity.getEmail(),
				userEntity.getPassword(), 
				userEntity.getFirstName(),
				userEntity.getLastName(),
				userEntity.getRoleId(),
				userEntity.getBusinessId()
				);
		if(updated > 0) return true;
		return false;
	}
	
	@Override
	public UserEntity getUserByUsername(String username) throws Exception{
		String sql = "select * from USERS where username = ? ";
		List<UserEntity> userEntities = jdbcTemplate.query(sql, new Object[] {username}, new UserEntityMapper());
		return userEntities.size() > 0 ? userEntities.get(0) : null;
	}
	
	@Override
	public UserEntity getUserByEmail(String email) throws Exception{
		String sql = "select * from USERS where email = ?";
		List<UserEntity> userEntities = jdbcTemplate.query(sql, new Object[] {email}, new UserEntityMapper());
		return userEntities.size() > 0 ? userEntities.get(0) : null;
	}
	
	
	@Override
	public Boolean existsByUsername(String Username) throws Exception {
		String sql = "SELECT COUNT(*) FROM USERS WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, Username);
        return count != null && count > 0;
	}
	

	@Override
	public Boolean existsByEmail(String email) throws Exception {
		String sql = "SELECT COUNT(*) FROM USERS WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
	}

	
}
