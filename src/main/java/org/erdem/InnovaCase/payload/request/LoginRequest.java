package org.erdem.InnovaCase.payload.request;

import org.erdem.InnovaCase.utils.Errors;


public class LoginRequest {
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String Validate() {

  	  	String msg="";
		
		// validating email field with required, min length 5, max length 25 and regex check
		msg = Errors.ValidateRequireAndLengthAndRegex(this.email, true, 5, 60, "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", "Email");
		if(!msg.isEmpty()) {
			return msg;
		}

		
		// validating password field with required, min length 8, max length 25 and regex check
		// ^[a-zA-Z0-9_!@#$_%^&*.?()-=+]*$
		msg = Errors.ValidateRequireAndLengthAndRegex(this.password, true, 6, 30, "", "Password");
		if(!msg.isEmpty()) {
			return msg;
		}
		
		return msg;

  	}
}
