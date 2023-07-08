package org.erdem.InnovaCase.exception;

import org.springframework.core.NestedRuntimeException;


public class UserEmailNotFoundException  extends NestedRuntimeException  {

	private static final long serialVersionUID = 1L;
	
	public UserEmailNotFoundException(String email) {
        super(String.format("User with  email '%s' not found", email));
    }


}
