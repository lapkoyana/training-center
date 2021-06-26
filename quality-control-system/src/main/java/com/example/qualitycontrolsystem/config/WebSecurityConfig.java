package com.example.qualitycontrolsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.example.qualitycontrolsystem.service.UserService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
		        .ignoringAntMatchers("/lections/*/remove",
		        		"/lections/*/questions/**",
		        		"/completed-lections/*/interview/*",
		        		"/students")
		    .and()
			.authorizeRequests()
				.antMatchers("/", "/registration").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout()
				.permitAll()
			.and()
				.rememberMe()
				.key("key")
				.rememberMeCookieName("remember-me-cookie")
				.rememberMeParameter("remember-me");
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userService)
        .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
