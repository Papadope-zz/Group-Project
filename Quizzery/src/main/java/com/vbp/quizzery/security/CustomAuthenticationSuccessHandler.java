package com.vbp.quizzery.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			return;

		}
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication auth) {

		String url = "/login?error=true";

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		List<String> roles = new ArrayList<>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());

		}
		if (roles.contains("PREMIUM")) {
			url = "/dashboard";
		} else if (roles.contains("SIMPLE")) {
			url = "/userdashboard";
		}
		return url;
	}
}
