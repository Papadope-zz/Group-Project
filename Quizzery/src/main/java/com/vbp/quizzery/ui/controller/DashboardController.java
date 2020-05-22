package com.vbp.quizzery.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

	@RequestMapping("/dashboard**")
	public String premiumDash() {

		return "dashboard";

	}

	@RequestMapping("/userdashboard**")
	public String simpleDash() {

		return "userdashboard";

	}

	@RequestMapping("/payment")
	public String payment() {

		return "payment";

	}

	@RequestMapping("/users**")
	public String users() {

		return "users";

	}

	@RequestMapping("/settings**")
	public String settings() {

		return "settings";

	}

	@RequestMapping("/publish**")
	public String publish() {

		return "publish";

	}

	@RequestMapping("/analyze**")
	public String analyze() {

		return "analyze";

	}

	@RequestMapping("/takequiz**")
	public String takequiz() {

		return "takequiz";

	}

	@RequestMapping("/editQuiz**")
	public String editQuiz() {

		return "editQuiz";

	}

}
