package org.erdem.InnovaCase.controllers;

import org.erdem.InnovaCase.model.User;
import org.erdem.InnovaCase.payload.request.LoginRequest;
import org.erdem.InnovaCase.payload.request.SignupRequest;
import org.erdem.InnovaCase.payload.response.JwtResponse;
import org.erdem.InnovaCase.payload.response.MessageResponse;
import org.erdem.InnovaCase.security.jwt.JwtUtils;
import org.erdem.InnovaCase.security.services.UserDetailsImpl;
import org.erdem.InnovaCase.service.AuthService;
import org.erdem.InnovaCase.utils.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
    private final AuthService authService;
	private final PasswordEncoder encoder;
	private final JwtUtils jwtUtils;
	
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, AuthService authService,
			PasswordEncoder encoder, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.authService = authService;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}

	/**
	 * AuthenticateUser it is used to Sing In a user 
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		
		String val = loginRequest.Validate();
		if(!val.isEmpty()){
			return ResponseEntity.ok(Errors.NewHTTPCustomError(Errors.BadRequest,val,HttpStatus.BAD_REQUEST.value()));
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getFirstName(),
												 userDetails.getLastName(),
												 userDetails.getEmail(), 
												 roles.get(0),
												 userDetails.getIsActive()
												 ));
	}

	/**
	 * RegisterUser it is user to sign up a user
	 * @param signUpRequest
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {

		String val = signUpRequest.Validate();
		if(!val.isEmpty()){
			return ResponseEntity.ok(Errors.NewHTTPCustomError(Errors.BadRequest,val,HttpStatus.BAD_REQUEST.value()));
		}
		
		if (authService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.ok(Errors.NewHttpError(Errors.UserAlreadyExists,HttpStatus.NOT_FOUND.value()));
		}

		User user = new User(signUpRequest.getFirstName(),signUpRequest.getLastName(),signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()));
		
		// Create new user's account
		authService.saveUser(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


	@GetMapping("/erdem")
	public ResponseEntity<?> erdem() {

		return ResponseEntity.ok(new MessageResponse("erdem hello"));
	}
	
}