package com.m2i.filRouge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {


	@Bean
	public SecurityFilterChain sfc(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorizeRequests ->
				authorizeRequests
					.antMatchers("/admin/*").hasRole("ADMIN")
		            .antMatchers("/user/*").hasRole("USER")
		            .anyRequest().authenticated()
		            .and()
			)
			 .formLogin(loginConfigurer ->
	            loginConfigurer
	                .successHandler((request, response, authentication) -> {
	                    // Redirect to the desired success URL
	                    response.sendRedirect("http://localhost:8080/filRouge/admin/index.html");
	                })
	                .failureUrl("/login?error=true")
	               
	        ).logout()
			 .logoutUrl("/logout")
			 .logoutSuccessUrl("/login")
			 .permitAll();
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService defUsersInMemory() {
		PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails user = User.builder()
				.username("user")
				.password(delegatingPasswordEncoder.encode("user"))
				.roles("USER")
				.build();
		UserDetails user2 = User.builder()
				.username("admin")
				.password(delegatingPasswordEncoder.encode("admin"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user, user2);
	}

}
