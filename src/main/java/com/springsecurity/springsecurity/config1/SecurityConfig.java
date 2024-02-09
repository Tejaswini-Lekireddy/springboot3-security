package com.springsecurity.springsecurity.config1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springsecurity.springsecurity.service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	
	 @Bean
	    //authentication
	    public UserDetailsService userDetailsService() {
			/*
			 * UserDetails admin = User.withUsername("admin")
			 * .password(encoder.encode("pw1") ) .roles("ADMIN") .build(); UserDetails user
			 * = User.withUsername("user") .password(encoder.encode("pw2")) .roles("USER")
			 * .build(); return new InMemoryUserDetailsManager(admin,user);
			 */
		 return new UserInfoUserDetailsService();
	 }
	 
	 
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.csrf().disable()
	                .authorizeHttpRequests()
	                .requestMatchers("/secure/newuser" , "/secure/home").permitAll()
	                .and()
	                .authorizeHttpRequests().requestMatchers("/secure/**")
	                .authenticated().and().formLogin().and().build();
	    }
	 
	 
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 @Bean
	    public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }
	 }

