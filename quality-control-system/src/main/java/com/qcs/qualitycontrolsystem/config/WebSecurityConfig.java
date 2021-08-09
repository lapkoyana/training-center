package com.qcs.qualitycontrolsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.qcs.qualitycontrolsystem.entity.Role;
import com.qcs.qualitycontrolsystem.service.UserService;

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserService userService;
//    
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.csrf().disable()
//			.authorizeRequests()
//				.antMatchers("/").permitAll()
//				.antMatchers("/lections").hasAuthority(Role.LECTURER.toString())
//				.antMatchers("/lessons/**").hasAuthority(Role.STUDENT.toString())
//				.anyRequest().authenticated()
//			.and()
//				.formLogin().permitAll()
//			.and()
//				.rememberMe()
//				.key("key")
//				.rememberMeCookieName("remember-me-cookie")
//				.rememberMeParameter("remember-me")
//			.and()
//				.httpBasic();
//	}
//	
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth.userDetailsService(userService)
//        .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }
//}
