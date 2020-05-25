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
	private final CustomAuthenticationSuccessHandler successHandler;
	private CustomLogoutHandler logoutHandler;

	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder,
			CustomAuthenticationSuccessHandler successHandler, CustomLogoutHandler logoutHandler) {

		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.successHandler = successHandler;
		this.logoutHandler = logoutHandler;
	}

	@Override // configures some of the entry points as public or as protected
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
				.permitAll().antMatchers(HttpMethod.GET, "/").permitAll().antMatchers("/css/**", "/js/**", "/images/**")

				.permitAll().antMatchers("/confirm-account").permitAll().antMatchers("/accountVerified").permitAll()
				.antMatchers("/403").permitAll().antMatchers("/404").permitAll()

				.antMatchers("/dashboard/**").hasAuthority("PREMIUM").antMatchers("/editQuiz/**")
				.hasAuthority("PREMIUM").antMatchers("/getQuizzeryChat/**").hasAuthority("PREMIUM")

				.anyRequest()

				.authenticated().and().formLogin().loginPage("/").permitAll().successHandler(successHandler).and()
				.rememberMe().and().logout().logoutUrl("/logout").clearAuthentication(true).invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "remember-me").logoutSuccessUrl("/").addLogoutHandler(logoutHandler);

	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

}
