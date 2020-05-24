package com.vbp.quizzery.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbp.quizzery.io.entity.ChatUserEntity;
import com.vbp.quizzery.io.entity.UserEntity;
import com.vbp.quizzery.io.repositories.ChatUserRepository;
import com.vbp.quizzery.io.repositories.UserRepository;
import com.vbp.quizzery.shared.dto.UserDto;
import com.vbp.quizzery.ui.model.response.UserRest;

@RestController
@CrossOrigin
public class ChatUserController {

	@Autowired
	ChatUserRepository cR;
	@Autowired
	UserRepository uR;

	@GetMapping("/registration")
	public UserRest register(@AuthenticationPrincipal User user) {

		String loggedEmail = user.getUsername();
		System.out.println("handling register user request: " + loggedEmail);

		UserEntity loggedUser = uR.findByEmail(loggedEmail);

		ChatUserEntity cE = new ChatUserEntity(loggedUser);

		try {
			cR.save(cE);

		} catch (Exception e) {

			System.out.println(e);

		}

		UserRest returnValue = new UserRest();

		BeanUtils.copyProperties(loggedUser, returnValue);
		return returnValue;

	}

	@GetMapping("/fetchAllUsers")
	public List<UserDto> fetchAll() {

		List<UserDto> returnValue = new ArrayList<>();

		List<ChatUserEntity> chatUserList = (List<ChatUserEntity>) cR.findAll();

		for (ChatUserEntity chatUser : chatUserList) {
			returnValue.add(new ModelMapper().map(chatUser.getUser(), UserDto.class));
		}

		return returnValue;
	}

}
