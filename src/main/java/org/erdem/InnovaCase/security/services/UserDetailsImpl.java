package org.erdem.InnovaCase.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.erdem.InnovaCase.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * UserDetailsImpl class implements the UserDetails of security, and performs custom authentication for user authentication users
 * @author Remy Yactayo
 *
 */
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	@JsonIgnore
	private String password;
	
	private boolean isActive;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String id, 
			String firstName,
			String lastName,
			String email, 
			String password,
			boolean isActive,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isActive = isActive;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority((user.getRole())));
				//user.getRole().stream()
				//.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				//.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getPassword(),
				user.getIsActive(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
	
	public boolean getIsActive() {
		return isActive;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	
}