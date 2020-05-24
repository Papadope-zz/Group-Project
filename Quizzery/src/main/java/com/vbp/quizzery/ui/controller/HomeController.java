package com.vbp.quizzery.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {

	@RequestMapping("/")
	public String home() {

		return "index";

	}

	@RequestMapping("/loginform")
	public String loginform() {

		return "loginform";

	}

	@RequestMapping("/getQuizzeryChat")
	public String getChat() {
		return "messageChatBox";
	}

}
