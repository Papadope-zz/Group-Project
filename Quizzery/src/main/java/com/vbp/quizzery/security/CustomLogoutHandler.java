package com.vbp.quizzery.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbp.quizzery.io.entity.UserEntity;
import com.vbp.quizzery.io.repositories.ChatUserRepository;
import com.vbp.quizzery.io.repositories.UserRepository;

@Service
public class CustomLogoutHandler implements LogoutHandler {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ChatUserRepository chatUserRepository;

	public CustomLogoutHandler() {

	}

	@Override
	@Transactional
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		// getting the logged user...
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username;

		if (principal instanceof UserDetails) {

			username = ((UserDetails) principal).getUsername();

		} else {

			username = principal.toString();

		}

		// ... log him out from the chat
		UserEntity user = userRepository.findByEmail(username);
		chatUserRepository.deleteByUser(user);

	}
}