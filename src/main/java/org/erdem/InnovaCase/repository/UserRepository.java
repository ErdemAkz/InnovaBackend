package org.erdem.InnovaCase.repository;

import org.erdem.InnovaCase.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository{
    
   /**
    * Find a users list     
    * @return
    */
	Optional<List<User>> findAll();

    /**
     * Find a user
     * @param userId
     * @return
     */
    public Optional<User> findById(String userId);

    /**
     * save a new user
     * @param user
     * @return
     */
    public User saveUser(User user);

    /**
     * Update a user
     * @param user
     */
    public void updateUser(User user);

    /**
     * Delete a user by id
     * @param userId
     */
    public void deleteUser(String userId);


	public Optional<User> findByEmail(String email);



}
