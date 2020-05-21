package com.vbp.quizzery.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.vbp.quizzery.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto user);

	UserDto getUser(String email);
	
	UserDto getUserByUserName(String userName);
	UserDto getUserByUserId(String userId);

	UserDto updateUser(String userId, UserDto userDto);

	void deleteUser(String userId);
}
