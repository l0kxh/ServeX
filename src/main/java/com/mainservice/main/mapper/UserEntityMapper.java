package com.mainservice.main.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mainservice.main.entities.UserEntity;

public class UserEntityMapper implements RowMapper<UserEntity>{

	@Override
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(rs.getLong("userId"));
		userEntity.setUsername(rs.getString("username"));
		userEntity.setEmail(rs.getString("email"));
		userEntity.setPassword(rs.getString("password"));
		userEntity.setFirstName(rs.getString("firstName"));
		userEntity.setLastName(rs.getString("lastName"));
		userEntity.setRoleId(rs.getLong("roleId"));
		userEntity.setBusinessId(rs.getLong("buisnessId"));
		userEntity.setCreatedAt(rs.getTimestamp("createdAt"));
		userEntity.setUpdatedAt(rs.getTimestamp("updatedAt"));
		return userEntity;
	}
}
