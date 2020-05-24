package com.vbp.quizzery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.vbp.quizzery.security.AppProperties;

@SpringBootApplication
public class QuizzeryApplication  /* extends SpringBootServletInitializer */{

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//
//		return builder.sources(QuizzeryApplication.class);
//	}

	public static void main(String[] args) {

		SpringApplication.run(QuizzeryApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationCopntext() {
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}

}
