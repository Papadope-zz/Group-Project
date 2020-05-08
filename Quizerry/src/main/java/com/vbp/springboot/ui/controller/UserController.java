package com.vbp.springboot.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbp.springboot.service.UserService;
import com.vbp.springboot.shared.dto.UserDto;
import com.vbp.springboot.ui.model.request.UserDetailsRequestModel;
import com.vbp.springboot.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public String getUser() {
		return "get user called";

	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

		UserRest returnValue = new UserRest(); // object that contains properties that we can send back to user

		UserDto userDto = new UserDto();// Data transfer object , we pass it around between layers with all the data
		BeanUtils.copyProperties(userDetails, userDto); // get user data into a Data transfer object
		UserDto createdUser = userService.createUser(userDto); // service will add business logic

		BeanUtils.copyProperties(createdUser, returnValue);

		return returnValue;
	}

	@PutMapping
	public String updateUser() {

		return "update was called";

	}

	@DeleteMapping
	public String deleteUser() {
		return "delete was caled";
	}

}
