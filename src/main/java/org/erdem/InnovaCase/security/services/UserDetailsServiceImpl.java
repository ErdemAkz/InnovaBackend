package org.erdem.InnovaCase.security.services;

import org.erdem.InnovaCase.model.User;
import org.erdem.InnovaCase.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsServiceImpl helps to create a UserDetails from a String-based email and is used by AuthenticationProvider
 * @author Remy Yactayo
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final AuthService authService;
	
	@Autowired
	public UserDetailsServiceImpl(AuthService authService) {
		this.authService = authService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = authService.findByEmail(email);
		
		//System.out.println(user);
		return UserDetailsImpl.build(user);
	}

}