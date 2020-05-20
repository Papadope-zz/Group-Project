package com.vbp.quizzery.ui.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vbp.quizzery.service.UserService;
import com.vbp.quizzery.shared.dto.UserDto;



@Controller
public class DashboardController {

	@Autowired
	UserService uS;

	
	@RequestMapping("/dashboard")
	public String home() {
	
	//@AuthenticationPrincipal UserDto user	
		
	// user=uS.getUser(user.getEmail());

//		ModelMapper mM = new ModelMapper();
//		UserDto userDto = mM.map(userDetails, UserDto.class);
		
 	//	if (user!=null) System.out.println( user.getUserName()+user.getUserId());

		return "dashboard";

}
}
