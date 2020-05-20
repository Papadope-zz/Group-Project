package com.vbp.quizzery.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.vbp.quizzery.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {

		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override // configures some of the entry points as public or as protected
	protected void configure(HttpSecurity http) throws Exception {
				
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
				.permitAll().antMatchers(HttpMethod.GET, "/").permitAll().antMatchers("/css/**", "/js/**", "/images/**")
				.permitAll()

				.anyRequest().authenticated().and().formLogin().loginPage("/").permitAll()
				.defaultSuccessUrl("/dashboard", true).and().rememberMe().and().logout().logoutUrl("/logout")
				.clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID", "remember-me")
				.logoutSuccessUrl("/");

	}

	@Override // setting the AuthenticationManagerBuilder the user details service that we
				// will use and the encryption method we use to protect the user password
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	public AuthenticationFilter getAutheticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");

		return filter;
	}
}
