package org.erdem.InnovaCase.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Errors {
	
	//Error codes
	public static final String InvalidUserID = "invalidUserID";
	public static final String InternalError = "internalError";
	public static final String UserNotFound = "userNotFound";
	public static final String InvalidBindingModel = "invalidBindingModel";
	public static final String Unauthorized = "unauthorized";
	public static final String BadRequest = "badRequest";
	public static final String UserAlreadyExists = "userAlreadyExists";
	
	/**
	 * Error code with decription
	 */
	private static Map<String, String> errorMessage = new HashMap<String,String>() {
		 
		private static final long serialVersionUID = 1L;

	{
	    put( "invalidUserID", "invalid user id");
	    put( "internalError", "an internal error occured" );
	    put( "userNotFound", "user could not be found" );
		put( "invalidBindingModel", "model could not be bound" );
		put( "unauthorized", "an unauthorized access" );
		put( "badRequest", "an unauthorized access" );
		put( "userAlreadyExists", "user already exists" );
	}};	
	
	/**
	 * NewHTTPError creates error model that will send as http response
	 * @param errorCode
	 * @param statusCode
	 * @return
	 */
	public static Map<String, String> NewHttpError(String errorCode ,int statusCode ){
		Map<String, String> m = new HashMap<String,String>();
		m.put("error", errorCode);
		m.put("error_description", errorMessage.get(errorCode));
		m.put("code", String.valueOf(statusCode));
		
		return m;
	}
	
	/**
	 *  NewHTTPCustomError creates error model that will send as http response
	 * @param errorCode
	 * @param errorMsg
	 * @param statusCode
	 * @return
	 */
	public static Map<String, String> NewHTTPCustomError(String errorCode, String errorMsg ,int statusCode ){
		Map<String, String> m = new HashMap<String,String>();
		m.put("error", errorCode);
		m.put("error_description", errorMsg );
		m.put("code", String.valueOf(statusCode));
		
		return m;
	}
	
	/**
	 * ValidateRequireAndLengthAndRegex is used to validate any input data but in string type
	 * @param value
	 * @param isRequired
	 * @param minLength
	 * @param maxLength
	 * @param regex
	 * @param fieldName
	 * @return
	 */
	public static String ValidateRequireAndLengthAndRegex(String value ,boolean isRequired ,  int minLength,  int maxLength,String regex, String fieldName ){
		String msg="";
		int length = value.length();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		
		if(isRequired && length < 1){
			return msg = fieldName + " is Required";
		}
		
		if(minLength != 0 && length > 0 && length < minLength) {
			return msg = fieldName + " must be min " + minLength;
		}
		
		if(maxLength != 0 && length > 0 && length > maxLength) {
			return msg = fieldName + " must be max " + maxLength;
		}
		
		if(!regex.isEmpty() && !matcher.matches()) { // Regex check
			return  msg = "Invalid " + fieldName;
		}
		return msg;
	}
	
	//errorMessage.put("InvalidUserID", "invalid user id");
}
