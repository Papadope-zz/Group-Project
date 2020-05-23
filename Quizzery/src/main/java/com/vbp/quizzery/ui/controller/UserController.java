package com.vbp.quizzery.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbp.quizzery.security.UserRole;
import com.vbp.quizzery.service.UserService;
import com.vbp.quizzery.shared.dto.UserDto;
import com.vbp.quizzery.ui.model.request.UserDetailsRequestModel;
import com.vbp.quizzery.ui.model.response.OperationStatusModel;
import com.vbp.quizzery.ui.model.response.RequestOperationNames;
import com.vbp.quizzery.ui.model.response.RequestOperationStatus;
import com.vbp.quizzery.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}")
	public UserRest getUser(@PathVariable String id) {

		UserRest returnValue = new UserRest();

		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;

	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

		UserRest returnValue = new UserRest(); // object that contains properties that we can send back to user

		ModelMapper modelMapper = new ModelMapper();

		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		userDto.setUserRole(UserRole.SIMPLE);// register user with SIMPLE role

		userDto.setEmailVerificationStatus(false); // setting that the user has not verified his email

		System.out.println(userDto);

		UserDto createdUser = userService.createUser(userDto); // service will add business logic

		BeanUtils.copyProperties(createdUser, returnValue);

		returnValue = modelMapper.map(createdUser, UserRest.class);

		System.out.println(returnValue);

		return returnValue;
	}

	@PutMapping(path = "/{id}")
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {

		UserRest returnValue = new UserRest();

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		UserDto updatedUser = userService.updateUser(id, userDto);

		BeanUtils.copyProperties(updatedUser, returnValue);

		return returnValue;

	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();

		returnValue.setOperationName(RequestOperationNames.DELETE.name());

		userService.deleteUser(id);

		returnValue.setOperationName(RequestOperationStatus.SUCCESS.name());
		return returnValue;

	}

}
