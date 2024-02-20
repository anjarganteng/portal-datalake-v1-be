package id.co.telkomsigma.jwt;

import id.co.telkomsigma.portalapps.model.ApplicationRoles;
import id.co.telkomsigma.portalapps.model.ApplicationRolesMenus;
import id.co.telkomsigma.portalapps.model.ApplicationUsers;
import id.co.telkomsigma.portalapps.service.ApplicationUsersService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author smriti
 * on github
 * https://github.com/smreeti/spring-security-with-zuul
 */
@Component
public class JwtTokenProvider {

    public static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret-key}")
    private String storedSecretKey;

    private String secretKey;
    private ApplicationUsersService applicationUsersService;


    @Autowired
    public JwtTokenProvider(ApplicationUsersService applicationUsersService) {
        this.applicationUsersService = applicationUsersService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(storedSecretKey.getBytes());
    }

//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = applicationUsersService.findByEmail(getUsername(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }

    // method sementara untuk permission
    public Authentication getAuthentication(String token, String requestUrl) {
        UserDetails userDetails = applicationUsersService.findByEmail(getUsername(token), requestUrl);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        return (!Objects.isNull(bearerToken) && bearerToken.startsWith("Bearer")) ? bearerToken.substring(7, bearerToken.length()) : null;
    }

    public boolean validateToken(String token) {
        try {
            LOGGER.info("Secret key: " + secretKey);
            LOGGER.info("Token: " + token);

            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return (!claims.getBody().getExpiration().before(new Date()));
        } catch (JwtException | IllegalArgumentException e) {
            LOGGER.error("Expired or invalid JWT token");
            // throw new RuntimeException("Request not authorized");
            return false;
        }
    }

    public Claims getJwtBody(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean validateMenus(String token, String requestUrl) {

        String email = getUsername(token);

        ApplicationUsers applicationUsers = applicationUsersService.getByEmail(email);

        List<String> allowedMenus = new ArrayList<>();

        ApplicationRoles applicationRoles = applicationUsers.getApplicationRoles();

        for (ApplicationRolesMenus applicationRolesMenus : applicationRoles.getRoleMenus()) {

            allowedMenus.add(applicationRolesMenus.getListMenu().getPathBackend());

        }

        String[] urls = requestUrl.split("/");
        String modul = urls[4];

        if (!allowedMenus.contains(modul)) {
            return false;
        }
        return true;
    }
}
