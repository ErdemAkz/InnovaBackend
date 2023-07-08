package org.erdem.InnovaCase.exception;

import org.springframework.core.NestedRuntimeException;


public class UserNotFoundException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String id) {
        super(String.format("User id '%s' not found", id));
    }

}
