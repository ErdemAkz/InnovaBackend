package org.erdem.InnovaCase.utils;

import org.erdem.InnovaCase.security.services.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class Variables {
	
	/**
	 * GetLoggedInUserID returns current loggedIn user id
	 * it reads userId from request context and
 	 * return id as string.
	 * @return
	 */
	public static String GetLoggedInUserID(){
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		return userDetails.getId();
	}
	
}
