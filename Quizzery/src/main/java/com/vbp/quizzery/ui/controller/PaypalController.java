package com.vbp.quizzery.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.vbp.quizzery.io.entity.UserEntity;
import com.vbp.quizzery.io.repositories.UserRepository;
import com.vbp.quizzery.security.UserRole;
import com.vbp.quizzery.service.PaypalService;
import com.vbp.quizzery.ui.model.request.OrderRequestModel;

@Controller
public class PaypalController {

	@Autowired
	PaypalService service;

	@Autowired
	UserRepository userRepository;

	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";

	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") OrderRequestModel order) {

		try {
			// creating payment with paypal API
			Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(), "sale",
					order.getDescription(), "http://localhost:8080/Quizzery/" + CANCEL_URL,
					"http://localhost:8080/Quizzery/" + SUCCESS_URL);

			// redirecting to paypal to pay
			for (Links link : payment.getLinks()) {
				if (link.getRel().equals("approval_url")) {
					return "redirect:" + link.getHref();

				}
			}

		} catch (PayPalRESTException e) {

			e.printStackTrace();
		}

		return "redirect:/";

	}

	@GetMapping(value = CANCEL_URL)
	public String cancelPay() {
		return "cancelpayment";

	}

	@GetMapping(value = SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerID) {

		try {
			Payment payment = service.executePayment(paymentId, payerID);
			System.out.println(payment.toJSON());

			if (payment.getState().equals("approved")) {

				// getting the logged user...
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				String username;

				if (principal instanceof UserDetails) {

					username = ((UserDetails) principal).getUsername();

				} else {

					username = principal.toString();

				}

				// ... and turning him to PREMIUM
				UserEntity user = userRepository.findByEmail(username);
				user.setUserRole(UserRole.PREMIUM);
				userRepository.save(user);

				return "successpayment";
			}

		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/";
	}

}
