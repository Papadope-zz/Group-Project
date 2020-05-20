package com.vbp.quizzery.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.vbp.quizzery.SpringApplicationContext;
import com.vbp.quizzery.service.UserService;
import com.vbp.quizzery.shared.dto.UserDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String userName = ((User) authentication.getPrincipal()).getUsername();
		//String tokenSecret = new SecurityConstants().getTokenSecret();
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
		UserDto userDto = userService.getUser(userName);
		
		String redirect="dashboard"+"/"+userDto.getUserId();
//		String redirect="dashboard";
		System.out.println(redirect);
		response.sendRedirect(redirect);

	}

}
