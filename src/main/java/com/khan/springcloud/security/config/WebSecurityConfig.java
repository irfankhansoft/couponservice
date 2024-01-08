package com.khan.springcloud.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.khan.springcloud.security.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig { 
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.formLogin();
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/couponapi/coupons/{code:^[A-Z]*$}","/","/showGetCoupon", "/getCoupon")
                .hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/showCreateCoupon","/createCoupon","/createResponse").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/couponapi/coupons","/saveCoupon").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/getCoupon").hasAnyRole("USER", "ADMIN")
                .and().csrf(csrf -> csrf.disable());
		http.authenticationProvider(daoAuthenticationProvider());
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
 }
