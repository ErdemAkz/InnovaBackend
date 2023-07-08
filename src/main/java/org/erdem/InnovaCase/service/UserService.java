package org.erdem.InnovaCase.service;

import org.erdem.InnovaCase.model.User;

import java.util.List;


public interface UserService {

	/**
    * Find all users
    * @return controller
    */
    List<User> findAll();
	
	/**
     * Find a controller
     * @param userId
     * @return controller
     */
    User findById(String userId);

    /**
     * Update a user
     * @param user
     */
    void updateUser(User user);

    /**
     * Delete a user
     * @param userId
     */
    void deleteUser(String userId);

    double getTotalSpending(String userId);
	

}