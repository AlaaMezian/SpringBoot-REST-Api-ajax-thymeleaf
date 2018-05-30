package com.appcom.waffa.security;

import static com.appcom.waffa.utils.AppConstants.EXPIRATION_TIME;
import static com.appcom.waffa.utils.AppConstants.HEADER_STRING;
import static com.appcom.waffa.utils.AppConstants.SECRET;
import static com.appcom.waffa.utils.AppConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.appcom.waffa.entity.Token;
import com.appcom.waffa.entity.User;
import com.appcom.waffa.respository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
	private final static Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	//this is a middle were to generate the token and give it back to the client
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private UserRepository userRespoisitory;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRespoisitory) {
    	this.authenticationManager = authenticationManager;
    	this.userRespoisitory = userRespoisitory;
		setFilterProcessesUrl("/api/v1/login");
//    	setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/v1/login"));

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	AuthenticatedUser creds = new ObjectMapper()
                    .readValue(req.getInputStream(), AuthenticatedUser.class);
        	
        
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
    	
        String userName =  ((AuthenticatedUser) auth.getPrincipal()).getUsername();
        User user =userRespoisitory.findByUsername(userName);
        String token = Jwts.builder()
                .setSubject("JwtToken")
                .claim("userName", user.getUsername())
                .claim("id" , user.getId())
                .claim("userEmail", user.getUserEmail())
                .claim("mobileNumber", user.getMobileNumber())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        
        String reqToken =req.getHeader("device_token");
        Token device_token = new Token(reqToken);
        user.setTokenFCM(device_token);
        userRespoisitory.save(user);
        ServletOutputStream responseOutPutStream = res.getOutputStream();

        responseOutPutStream.print("access-token: " +token);
    }

}