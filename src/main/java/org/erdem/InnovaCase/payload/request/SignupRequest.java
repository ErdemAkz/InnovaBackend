package org.erdem.InnovaCase.payload.request;

import org.erdem.InnovaCase.utils.Errors;


public class SignupRequest {
 
	private String firstName;
	
	private String lastName;
	
    private String email;
       
    private String password;
  
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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
    
    /**
     * method to validate fields
     * @return
     */
    public String Validate() {

  	  String msg="";
  		// validating name field with retuired, min length 3, max length 25 and no regex check
  	  	msg= Errors.ValidateRequireAndLengthAndRegex(this.firstName, true, 2, 30, "", "FirstName");
  	  	 
  		if(!msg.isEmpty()) {
  			return msg;
  		}

  		msg =  Errors.ValidateRequireAndLengthAndRegex(this.lastName, true, 2, 40, "", "LastName");
  		if(!msg.isEmpty()) {
  			return msg;
  		}

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