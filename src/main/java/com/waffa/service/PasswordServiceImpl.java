package com.waffa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.waffa.entity.User;
import com.waffa.exceptions.BadRequestException;
import com.waffa.exceptions.ConflictException;
import com.waffa.exceptions.InternalServerErrorException;
import com.waffa.exceptions.NotFoundException;
import com.waffa.model.ChangePasswordModel;
import com.waffa.model.ForgetPasswordModel;
import com.waffa.respository.UserRepository;

@Service
public class PasswordServiceImpl implements PasswordService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired @Lazy
	private BCryptPasswordEncoder passwordEncoder;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public void changePassword(int userId, ChangePasswordModel changeMdl) {
		User user = userRepo.findByUserId(userId);
		
		logger.info("the user object " + user);
		if(user == null) 
		{
			throw new NotFoundException("Unable to update requested User,User is Not Found");
		}
		if(!passwordEncoder.matches(changeMdl.getOldPassword(), user.getPassword()))
		{
			throw new BadRequestException("please enter your valid old password");
		}
		
		user.setPassword(passwordEncoder.encode(changeMdl.getNewPassword()));
		userRepo.save(user);

	}



	@Override
	public void forgetPassowrd(String email, ForgetPasswordModel forgetPssMdl) {
		
		User user = userRepo.findByUserEmail(email);
		if(user == null) 
		{
			throw new BadRequestException("not registred email,please enter a valid email to update the password");
		}
		
		if(passwordEncoder.matches(forgetPssMdl.getNewPassword(), user.getPassword()))
		{
		throw new ConflictException("you entered your old password please re-enter password using new one ");	
		}
		
		user.setPassword(passwordEncoder.encode(forgetPssMdl.getNewPassword()));
		
		userRepo.save(user);
		
		
	}
	

}
