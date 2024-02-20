package id.co.telkomsigma.config;

import id.co.telkomsigma.jwt.JwtConfigurer;
import id.co.telkomsigma.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
     * use below method just for debugging purpose.
     *
     */
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//        	.httpBasic().disable()
//        	.csrf().disable()
//        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        	.and()
//        	.authorizeRequests().anyRequest().permitAll()
//        	.and()
//        	.apply(new JwtConfigurer(jwtTokenProvider));
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().and()
                .authorizeRequests()
                .antMatchers("/**/poke").permitAll()
                .antMatchers(
                        "/**/v2/api-docs",
                        "/**/configuration/ui",
                        "/**/swagger-resources/**",
                        "/**/configuration/security",
                        "/**/swagger-ui.html",
                        "/**/webjars/**").permitAll()  // swagger module
                .antMatchers("/antasena-service/application-users/login").permitAll()
                .antMatchers("/testing/**").permitAll()
                .antMatchers("/antasena-service/application-users/get-custom-menu").permitAll()
                .anyRequest().authenticated().and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
