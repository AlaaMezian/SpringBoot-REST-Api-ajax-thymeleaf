package com.waffa.security;

import static com.waffa.utils.AppConstants.EXPIRATION_TIME;
import static com.waffa.utils.AppConstants.HEADER_STRING;
import static com.waffa.utils.AppConstants.SECRET;
import static com.waffa.utils.AppConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waffa.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
	private final static Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	//this is a middle were to generate the token and give it back to the client
	@Autowired
	private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    	this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/api/v1/login");

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	logger.info("this is the input stream " + req.getInputStream());
        	AuthenticatedUser creds = new ObjectMapper()
                    .readValue(req.getInputStream(), AuthenticatedUser.class);
        	
        	logger.info("this is the username cred "+ creds.getUsername());
        	logger.info("this is the password cred" + creds.getPassword());
            logger.info("this is authentication token test" + authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( creds.getUsername(),
                            creds.getPassword(),  new ArrayList<>()))); 
        
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((AuthenticatedUser) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}