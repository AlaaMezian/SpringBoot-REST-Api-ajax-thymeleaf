package com.waffa.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.waffa.entity.User;
import com.waffa.exceptions.NotFoundException;
import com.waffa.respository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	 private UserRepository userRepository;

	 public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        }

	  
	@Override
	public AuthenticatedUser loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userRepository.findByUsername(username);
 		logger.info("what is the user object :"+ user);
		 if (user == null) {
//	            throw new UsernameNotFoundException("username " + username
//	                    + " not found");
				throw new NotFoundException("User Not Found");

	        }
		 else  {
				AuthenticatedUser LoggedinUser = new AuthenticatedUser(user.getUsername(), user.getPassword());
				LoggedinUser.setUser(user);
				return LoggedinUser;
		 }
		 //trying to implement right response format 
	}
	
	  

	
}
