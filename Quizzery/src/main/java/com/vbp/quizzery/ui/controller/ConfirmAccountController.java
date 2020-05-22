package com.vbp.quizzery.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vbp.quizzery.io.entity.UserEntity;
import com.vbp.quizzery.io.entity.VerificationToken;
import com.vbp.quizzery.io.repositories.UserRepository;
import com.vbp.quizzery.io.repositories.VerificationTokenRepository;

@Controller
public class ConfirmAccountController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@RequestMapping("/confirm-account")
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String verificationToken) {
		VerificationToken token = verificationTokenRepository.findByToken(verificationToken);

		if (token != null) {
			UserEntity user = userRepository.findByEmail(token.getUser().getEmail());
			user.setEmailVerificationStatus(true);
			userRepository.save(user);
			modelAndView.setViewName("accountVerified");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}

		return modelAndView;
	}

}
