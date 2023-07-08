package org.erdem.InnovaCase.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.erdem.InnovaCase.config.AppProperty;
import org.erdem.InnovaCase.exception.UserNotFoundException;
import org.erdem.InnovaCase.model.Transaction;
import org.erdem.InnovaCase.model.User;
import org.erdem.InnovaCase.repository.TransactionRepository;
import org.erdem.InnovaCase.repository.UserRepository;
import org.erdem.InnovaCase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PostRemove;
import java.util.List;
import java.util.Optional;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Log log = LogFactory.getLog(UserServiceImpl.class);
	
	private final UserRepository userRepository;
	private final AppProperty appProperty;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,AppProperty appProperty){
        this.userRepository = userRepository;
        this.appProperty = appProperty;
    }

	@Override
	public List<User> findAll() {
		Optional<List<User>> user = userRepository.findAll();
        return user.get();  
	}

	@Override
	public User findById(String userId) {
		Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            //log.debug(String.format("Read userId '{}'", userId));
            return user.get();
        }else
            throw new UserNotFoundException(userId);
	}

	@Override
	public void updateUser(User user) {
		userRepository.updateUser(user);
	}

	@Override
	public void deleteUser(String userId) {
		userRepository.deleteUser(userId);
	}

	@Override
	public double getTotalSpending(String userId){
    	User user = findById(userId);
    	List<Transaction> transactions = user.getTransactions();

    	double sum = 0.0;
    	for (int i =0; i< transactions.size(); i++){
    		sum += transactions.get(i).getAmount();
		}

    	return sum;
	}


}
