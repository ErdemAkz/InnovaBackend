package org.erdem.InnovaCase.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.erdem.InnovaCase.exception.UserEmailNotFoundException;
import org.erdem.InnovaCase.model.User;
import org.erdem.InnovaCase.repository.UserRepository;
import org.erdem.InnovaCase.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("authService")
public class AuthServiceImpl  implements AuthService  {
	
	private static final Log log = LogFactory.getLog(UserServiceImpl.class);
	
	private final UserRepository userRepository;
	
	@Autowired
	public AuthServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.saveUser(user);
	}

	@Override
	public User findByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            //log.debug(String.format("Read email '{}'", email));
            return user.get();
        }else{
        	log.debug(String.format("Email not found '{}'", email));
            throw new UserEmailNotFoundException(email);
        }
	}
	
	@Override
	public boolean existsByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		boolean isPresent = user.isPresent();
		return isPresent;
	}

}
