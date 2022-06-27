package com.jwatprojectdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jwatprojectdemo.service.impl.UserServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired UserServiceImpl uService;
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(uService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/api/**").hasAnyRole("director","staff")
		.antMatchers("/admin/**").hasAnyRole("director","staff")
		.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/account/login/form")
			.loginProcessingUrl("/account/login")
			.defaultSuccessUrl("/account/login/success",false)
			.failureUrl("/account/login/error");
		
		http.rememberMe()
			.tokenValiditySeconds(86400);
		
		http.exceptionHandling()	
			.accessDeniedPage("/account/access-denied");
		
		http.logout()
			.logoutUrl("/account/logout")
			.logoutSuccessUrl("/account/logout/success");
	}
}
