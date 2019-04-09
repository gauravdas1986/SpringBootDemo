//package com.cogni.apartment.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//@Configuration
//// Configuring this class prevents the Spring Boot autoconfiguration
//// from happening.
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	protected void configureUser(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
//		.and().withUser("admin").password("password").roles("ADMIN");
//	}
//
//	// We do not want the default behavior of form authentication
//	// before HTTP Basic authentication we get
//	// from WebSecurityConfigurerAdapter.
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
//	}
//}