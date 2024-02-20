package id.co.telkomsigma.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author smriti
 * on github
 * https://github.com/smreeti/spring-security-with-zuul
 */

public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);

        if (!Objects.isNull(token) && jwtTokenProvider.validateToken(token)) {
            // Authentication auth = jwtTokenProvider.getAuthentication(token);

            // fixing sementara permission tidak bisa didapatkan
            Authentication auth = jwtTokenProvider.getAuthentication(token, request.getRequestURL().toString());
            if (!Objects.isNull(auth)) {

                // start section check menu
                if (jwtTokenProvider.validateMenus(token, request.getRequestURL().toString())) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    response.setStatus(403);
                }
//                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } else if (!Objects.isNull(token) && !jwtTokenProvider.validateToken(token)) {
            response.setStatus(403);
        }

        filterChain.doFilter(request, response);
    }

}
