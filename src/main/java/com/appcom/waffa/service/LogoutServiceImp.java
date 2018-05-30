package com.appcom.waffa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcom.waffa.entity.Token;
import com.appcom.waffa.entity.User;
import com.appcom.waffa.exceptions.NotFoundException;
import com.appcom.waffa.respository.TokenRepository;
import com.appcom.waffa.respository.UserRepository;

@Service
public class LogoutServiceImp implements LogoutService{
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public TokenRepository tokenRepository;

	@Override
	public void logout(int userId) {
		User loggedInUser = userRepository.findById(userId);
		if(loggedInUser == null) 
		{
			throw new NotFoundException("the user is no longed logged in");
		}
		Token device_token = loggedInUser.getTokenFCM();
		String token = device_token.getToken();
		Token tokenEntity = tokenRepository.findOneByToken(token);
		System.out.println("token " + tokenEntity.getToken() +"token Id" + tokenEntity.getId());
		tokenRepository.delete(tokenEntity);
		loggedInUser.setTokenFCM(null);
		userRepository.save(loggedInUser);
		
	}

	
}
