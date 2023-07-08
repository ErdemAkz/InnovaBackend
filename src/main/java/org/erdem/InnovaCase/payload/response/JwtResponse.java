package org.erdem.InnovaCase.payload.response;

import org.erdem.InnovaCase.model.User;


public class JwtResponse {
	private User user;
	private String token;

	public JwtResponse(
			String accessToken, 
			String id, 
			String firstName,
			String lastName, 
			String email, 
			String role,
			boolean isActive
			) {
		this.token = accessToken;
		this.user = new User();
		this.user.setId(id);
		this.user.setFirstName(firstName);
		this.user.setLastName(lastName);
		this.user.setEmail(email);
		this.user.setRole(role);
		this.user.setIsActive(isActive);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}