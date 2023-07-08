
package org.erdem.InnovaCase.repository.impl;

import org.erdem.InnovaCase.model.User;
import org.erdem.InnovaCase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class UserRepositoryImpl implements UserRepository{

    private final MongoOperations mongoOperations;

    @Autowired
    public UserRepositoryImpl(MongoOperations mongoOperations) {
        //Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }
    
    @Override
    public Optional<List<User>> findAll() {
    	List<User> users = this.mongoOperations.find(new Query(), User.class);
        Optional<List<User>> optionalUsers = Optional.ofNullable(users);
        return optionalUsers;
	}    
   
    @Override
    public Optional<User> findById(String userId) {
        User d = this.mongoOperations.findOne(new Query(Criteria.where("_id").is(userId)), User.class);
        Optional<User> user = Optional.ofNullable(d);
        return user;
    }

    @Override
    public User saveUser(User user) {
        this.mongoOperations.save(user);
        return findById(user.getId()).get();
    }
    
    @Override
    public void updateUser(User user) {
    	
        this.mongoOperations.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        this.mongoOperations.findAndRemove(new Query(Criteria.where("id").is(userId)), User.class);
    }

	@Override
	public Optional<User> findByEmail(String email) {
		User d = this.mongoOperations.findOne(new Query(Criteria.where("email").is(email)), User.class);
        Optional<User> user = Optional.ofNullable(d);
        return user;
	}

}