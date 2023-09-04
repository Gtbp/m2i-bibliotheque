package com.m2i.filRouge.security;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;

public class CustomUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

	protected final Log logger = LogFactory.getLog(getClass());

	private final Map<String, UserDetails> users = new HashMap<>();

	private AuthenticationManager authenticationManager;

	// 1er
	public CustomUserDetailsManager(UserDetails... users) {
		System.out.println("CustomUserDetailsManager");
		for (UserDetails user : users) {
			createUser(user);
		}
	}
	// 3ème
	public UserDetails getNewUserDetails() {
		System.out.println("getNewUserDetails");
		PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails user = User.builder()
				.username("admin1")
				.password(delegatingPasswordEncoder.encode("admin"))
				.roles("ADMIN")
				.build();
		return user;
	}

	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	// à l'authentification
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return users.get(username);
	}


	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		System.out.println("updatePassword");
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void createUser(UserDetails user) {
		Assert.isTrue(!userExists(user.getUsername()), "user should not exist");
		this.users.put(user.getUsername().toLowerCase(), user);
	}


	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	// 2ème
	@Override
	public boolean userExists(String username) {
		System.out.println("userExists");
		// TODO Auto-generated method stub
		return false;
	}

}
