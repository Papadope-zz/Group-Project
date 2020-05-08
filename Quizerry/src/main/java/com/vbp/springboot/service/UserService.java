package com.vbp.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.vbp.springboot.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto user);

	UserDto getUser(String email);

}
