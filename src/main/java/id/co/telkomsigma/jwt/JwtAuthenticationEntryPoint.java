package id.co.telkomsigma.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author smriti
 * on github
 * https://github.com/smreeti/spring-security-with-zuul
 */

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -371327761130789585L;
    public static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        LOGGER.info("Jwt authentication failed:" + authException);

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT authentication failed");

    }
}
