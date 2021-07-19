package com.example.qualitycontrolsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        // @formatter:off
//        auth.inMemoryAuthentication()
//        .withUser("q").password("1").roles("STUDENT")
//        .and()
//        .withUser("w").password("2").roles("STUDENT")
//        .and()
//        .withUser("a").password("1").roles("LECTURER");
//    }
    
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
				.antMatchers(
		            HttpMethod.GET,
		            "/index*", "/static/**", "/*.js", "/*.json", "/*.ico")
		            .permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/index.html")
		        .loginProcessingUrl("/perform_login")
		        .defaultSuccessUrl("/homepage.html",true)
		        .failureUrl("/index.html?error=true")
			.and()
				.logout()
		        .logoutUrl("/perform_logout")
		        .deleteCookies("JSESSIONID")
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
