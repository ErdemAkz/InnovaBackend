package org.erdem.InnovaCase.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.erdem.InnovaCase.utils.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	/**
	 * Always returns a 401 error code to the client.
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		//System.out.println(request);
		logger.error("Unauthorized error: {}", authException.getMessage());
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(Errors.NewHttpError(Errors.Unauthorized,HttpStatus.UNAUTHORIZED.value()));
		response.setStatus(200);
	
		response.setContentType("application/json");
	    response.getWriter().write(json);
	}

}