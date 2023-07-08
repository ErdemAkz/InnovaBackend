package org.erdem.InnovaCase.service;

import org.erdem.InnovaCase.model.User;


public interface AuthService {
	
	/**
	* Save a new user
	* @param user
	* @return controller saved
	*/
	User saveUser(User user);

    /**
	 * Find user by email
	 * @param email
	 * @return
	 */
	User findByEmail(String email);
	
	/**
     * Check if email exits
     * @param userId
     */
	boolean existsByEmail(String email);

}
