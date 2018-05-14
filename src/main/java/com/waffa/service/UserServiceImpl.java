package com.waffa.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.waffa.constant.Status;
import com.waffa.entity.User;
import com.waffa.exceptions.BadRequestException;
import com.waffa.exceptions.InternalServerErrorException;
import com.waffa.model.RegistrationModel;
import com.waffa.respository.UserRepository;
import com.waffa.utils.CoreValidations;
import com.waffa.utils.CurrentDate;

@Service("userService")
public class UserServiceImpl implements UserService {

	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

	@Override
	public void saveUser(RegistrationModel registrationModel) {
		try {
			Date currentDate = CurrentDate.getCurrentDate();
			User userEntity = new User();

			if (!CoreValidations.isAlphaNumeric(registrationModel.getUserName())) {
				throw new BadRequestException("userName is not valid is should only contain letters and numbers");
			}

			// Email validation
			if (!CoreValidations.isEmailValid(registrationModel.getUserEmail())) {
				throw new BadRequestException("email is not valid please enter a valid email format ");
			}

			User userExists = userRepository.findByUserEmail(registrationModel.getUserEmail());
			if (userExists != null) {
				throw new BadRequestException("user already signedUp,please SignUp with an other email");
			}

			User userFoundByUserName = userRepository.findByUsername(registrationModel.getUserName());
			if (userFoundByUserName != null) {
				throw new BadRequestException("the userName you are trying to signUp with is already taken");
			}

			userEntity.setUsername(registrationModel.getUserName());
			userEntity.setUserEmail(registrationModel.getUserEmail());
			userEntity.setPassword(bCryptPasswordEncoder.encode(registrationModel.getPassword()));
			userEntity.setIsActive(Status.Y);
			userEntity.setMobileNumber(registrationModel.getMobileNumber());
			userEntity.setRegDate(currentDate);
			userRepository.save(userEntity);
		} catch (InternalServerErrorException ex) {
			logger.error("---------------------------------------------------------------------------------------");
			logger.error("Error when sign up user", ex);
			logger.error("----------------------------------------------------------------------------------------");
			throw ex;
		} 
	}

}
